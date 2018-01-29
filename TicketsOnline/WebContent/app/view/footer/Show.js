Ext.define('app.view.footer.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.footershow',
    border: true,

    initComponent: function() {
    	console.log('Inicializando vista pie...');
    	this.items = [
    		{
    		},
    	]
    	this.callParent(arguments);
    }
});