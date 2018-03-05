Ext.application({
    requires: ['Ext.panel.Panel'],
    name: 'app',

    appFolder: 'app',
    
    controllers:[
    	'Grid',
    	'Seats'
    ],

    launch: function() {
    	new Ext.Panel({
        	renderTo:	Ext.getBody(),
        	border: false,
        	items: 
        	[
				{
					name: 'seatsshow',
					id: 'seatsshow',
					xtype: 'seatsshow'
				}
            ]
        });
    }
});