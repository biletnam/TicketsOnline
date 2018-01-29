Ext.define('app.view.header.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.headershow',

    initComponent: function() {
    	console.log('Inicializando vista encabezado...');
    	this.layout = 'fit';
        this.border = false;
    	this.items = [
    		{
    			xtype: 'image',
		    	src: 'img/logo.png',
    		}
    	]
    	this.callParent(arguments);
    }
});