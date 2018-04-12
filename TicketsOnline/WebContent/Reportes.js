Ext.application({
	name: 'app',
    appFolder: 'app',

    controllers:[
    	'Menu',
    	'GlobalSale',
    ],
    
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            name: 'all',
            id: 'all',
            items: [
            	{
	                region: 'west',
	                width: 100,
              		xtype: 'menushow'
            	}, 
            	{
	                region: 'center',
	                layout: {
	                	type: 'fit'
	                }
            	}
            ]
        });
    }
});