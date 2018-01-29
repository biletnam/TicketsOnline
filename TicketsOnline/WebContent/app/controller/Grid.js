Ext.define('app.controller.Grid', {
    extend: 'Ext.app.Controller',

    stores: ['Grid'],
    
    models: ['Grid'],
    
    views:[
    	'grid.Show'
    ],
    
    init: function() {
        console.log('Inicializando controlador grid...');
    }
    
});