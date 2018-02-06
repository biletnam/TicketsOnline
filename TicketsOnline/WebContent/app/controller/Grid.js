Ext.define('app.controller.Grid', {
    extend: 'Ext.app.Controller',

    stores: ['Grid'],
    
    models: ['Grid'],
    
    views:[
    	'grid.Show'
    ],
    
    init: function() {
        console.log('Inicializando controlador grid...');
        this.control({
        	'gridshow':{
        		onLayoutBack: this.onLayoutBack
        	}
        });
    },
    
    onLayoutBack: function(){
    	Ext.data.StoreManager.lookup('Grid').removeAll();
    	Ext.getCmp('subTotal').setValue(0);
    	Ext.getCmp('tax').setValue(0);
    	Ext.getCmp('total').setValue(0);
    	Ext.getCmp('buyBtn').setDisabled(true);
    }
    
});