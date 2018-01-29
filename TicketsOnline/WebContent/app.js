Ext.application({
    requires: ['Ext.panel.Panel'],
    name: 'app',

    appFolder: 'app',
    
    controllers:[
    	'Grid',
    	'Poster',
    	'Body',
    	'Header',
    	'Stage',
    	'Buy'
    ],

    launch: function() {
        
    	new Ext.Panel({
        	
        	renderTo:	Ext.getBody(),
        	
        	border: false,
            
        	items: [
            	{
                    name: 'headershow01',
                    id: 'headershow01',
                    xtype: 'headershow',
                },
	            {	
                	xtype: 'panel',
                	name: 'bodyshow02',
                    id: 'bodyshow02',
                	layout: 'card',
            		activeItem:	0,
            		items:[
            			{
            				name: 'bodyshow01',
            				id: 'bodyshow01',
            				xtype: 'bodyshow'
            			},
            			{
            				name: 'stageshow01',
            				id: 'stageshow01',
            				xtype: 'stageshow'
            			},
            			{
            				name: 'buy01',
            				id: 'buy01',
            				xtype: 'buyshow'
            			}
            		]
	            }
            ]
        	
        });
    }
});