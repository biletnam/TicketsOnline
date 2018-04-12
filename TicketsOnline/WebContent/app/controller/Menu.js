Ext.define('app.controller.Menu', {
    extend: 'Ext.app.Controller',

    stores: ['Menu'],
    
    views:['menu.Show'],
    
    init: function() {
        console.log('Inicializando controlador menu...');
        this.control({
        	'menushow':{
        		select: this.openPanel
        	}
        })
    },
    
    openPanel: function(me, record, item, index, e){
    	var viewPort = Ext.ComponentQuery.query('viewport')[0];
    	var widget = record.raw.widget;
    	console.log(widget);
		var region = viewPort.down('panel[region=center]');
		region.removeAll();
		var panel = Ext.widget(widget);
		region.add(panel);
    }
});