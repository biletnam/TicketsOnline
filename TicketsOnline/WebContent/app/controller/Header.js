Ext.define('app.controller.Header', {
    extend: 'Ext.app.Controller',

    views:[
    	'header.Show'
    ],
    
    init: function() {
        console.log('Inicializando controlador encabezado...');
    }
});