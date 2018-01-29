Ext.define('app.view.body.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.bodyshow',
    
    layout: {
    	type: 'table',
    	columns: 2,
    	tableAttrs: {
	        style: {
	           width: '100%',
	           height: '100%'
	        }
        }  
    },
    
    defaults: {
        bodyStyle:'padding:20px'
    },

    border: false,
    
    initComponent: function() {
    	console.log('Inicializando vista cuerpo...');
    	this.callParent(arguments);
    }
    
});