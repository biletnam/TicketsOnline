Ext.define('app.view.poster.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.postershow',
    name: 'postershow01',
    
    border: false,
    
	layout: {
    	type: 'hbox',
    },
    
    defaults:{
		bodyStyle:'padding:10px'
	},
    
    initComponent: function() {
    	this.items = [
    		{
    			xtype: 'image',
    			name: 'image',
    			src: 'img/event00001.jpg',
    			flex: 1
    		},
    		{
    			xtype: 'form',
    			name: 'postershow02',
				border: false,
				defaults:{
					anchor:	'-10'
				},
    			items:[
    				{
    					xtype: 'hiddenfield',
    					name: 'hidEvent'
    				},
    				{
    					xtype: 'hiddenfield',
    					name: 'hidStageFileName'
    				},
    				{	
    					name: 'artist',
    					xtype: 'displayfield',
    					fieldCls:'size18b',
    				},
    				{	
    					name: 'date',
    					xtype: 'displayfield',
    					fieldCls:'size18',
    				},
    				{	
    					name: 'time',
    					xtype: 'displayfield',
    					fieldCls:'size18',
    				},
    				{
    					name: 'place',
    					xtype: 'displayfield',
    					fieldCls:'size18',
    					height: 100
    				}
    			],
    			dockedItems: [
    		        {
    		            xtype: 'toolbar',
    		            name: 'toolbar',
    		            dock: 'bottom',
    		            ui: 'footer',
    		            layout: {
    		                pack: 'end',
    		                type: 'hbox'
    		            },
    		            items: [
    		                {
    		                    xtype: 'button',
    		                    name: 'reservBtn',
    		                    text: 'Reservar',
    		                    itemId: 'reserv',
    		                }
    		            ]
    		        }
    		    ],
    			flex: 1
    		}
    	],
    	this.callParent(arguments);
    }
});