Ext.define('app.controller.Buy', {
    extend: 'Ext.app.Controller',

    stores: ['States', 'Months'],
    
    models: ['States', 'Months'],
    
    views:[
    	'buy.Show'
    ],
    
    init: function() {
        this.control({
        	'button[name=returnBtn02]':{
        		click: this.returnStage
        	},
        	'button[name=buyBtn02]':{
        		click: this.buy
        	}
        });
    },
    
    returnStage: function(button){
    	var panel01 = Ext.getCmp('bodyshow02');
    	
    	panel01.layout.setActiveItem(1);
    },
    
    buy: function(button){
	    var form = button.up('form').getForm();
	    
	    if (form.isValid()) {
	        Ext.MessageBox.alert('Informacion', form.getValues(true));
	    }else{
	    	Ext.MessageBox.alert('Advertencia', 'Favor de llenar todos los campos');
	    }
    }
});