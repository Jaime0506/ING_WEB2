/**
 * Scripts generales para la aplicación
 */
$(document).ready(function() {
    // Añadir efectos de hover a los elementos del menú
    $('.ui-menuitem').hover(
        function() { $(this).addClass('pulse'); },
        function() { $(this).removeClass('pulse'); }
    );
    
    // Animación para las notificaciones
    $('.notificacion').each(function() {
        $(this).delay(5000).fadeOut(500);
    });
    
    // Mejora de los botones
    $('.ui-button').not('.ui-datatable-header .ui-button').hover(
        function() { $(this).addClass('button-scale'); },
        function() { $(this).removeClass('button-scale'); }
    );
    
    // Inicializar tooltips para iconos
    initTooltips();
    
    // Manejar el ancho de las tablas responsivas
    $(window).resize(function() {
        adjustTableResponsiveness();
    });
    
    adjustTableResponsiveness();
});

// Inicializa tooltips para los iconos
function initTooltips() {
    $('.pi[data-tooltip]').each(function() {
        var tooltip = $(this).data('tooltip');
        $(this).attr('title', tooltip);
    });
}

// Ajusta la responsividad de las tablas
function adjustTableResponsiveness() {
    if ($(window).width() < 768) {
        $('.responsive-table').addClass('mobile-view');
    } else {
        $('.responsive-table').removeClass('mobile-view');
    }
}

// Filtra tablas de datos con retardo para mejorar rendimiento
function filterDataTable(tableId, input) {
    clearTimeout(window.filterTimeout);
    window.filterTimeout = setTimeout(function() {
        $(tableId).DataTable().search(input.value).draw();
    }, 300);
}

// Muestra un mensaje de carga
function showLoading(message) {
    if (!message) message = 'Cargando...';
    
    if (!$('#loading-overlay').length) {
        $('body').append('<div id="loading-overlay" class="loading-overlay">' +
                         '<div class="loading-content">' +
                         '<div class="loading-spinner"></div>' +
                         '<div class="loading-message">' + message + '</div>' +
                         '</div></div>');
    } else {
        $('#loading-overlay .loading-message').text(message);
        $('#loading-overlay').show();
    }
}

// Oculta el mensaje de carga
function hideLoading() {
    $('#loading-overlay').fadeOut(300);
}

// Muestra notificaciones personalizadas
function showNotification(type, title, message, duration) {
    if (!duration) duration = 5000;
    
    // Tipo: success, warning, error, info
    var icon = 'info-circle';
    var bgColor = 'var(--color-primary)';
    
    switch(type) {
        case 'success':
            icon = 'check-circle';
            bgColor = 'var(--color-success)';
            break;
        case 'warning':
            icon = 'exclamation-triangle';
            bgColor = 'var(--color-warning)';
            break;
        case 'error':
            icon = 'times-circle';
            bgColor = 'var(--color-danger)';
            break;
    }
    
    var notification = $('<div class="custom-notification" style="background-color:' + bgColor + '">' +
                        '<i class="pi pi-' + icon + '"></i>' +
                        '<div class="notification-content">' +
                        '<div class="notification-title">' + title + '</div>' +
                        '<div class="notification-message">' + message + '</div>' +
                        '</div>' +
                        '<i class="pi pi-times notification-close"></i>' +
                        '</div>');
    
    if (!$('#notification-container').length) {
        $('body').append('<div id="notification-container"></div>');
    }
    
    $('#notification-container').append(notification);
    
    notification.fadeIn(300).delay(duration).fadeOut(300, function() {
        $(this).remove();
    });
    
    notification.find('.notification-close').click(function() {
        notification.fadeOut(300, function() {
            $(this).remove();
        });
    });
} 