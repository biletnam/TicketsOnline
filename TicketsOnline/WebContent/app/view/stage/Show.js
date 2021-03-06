function actionSeat(seat_){
	var img = Ext.getCmp('StageImage');
	 	
 	img.fireEvent('onImageClick', img, seat_);
}

function back(layout_){
	window.history.back();
	Ext.getCmp('gridShow').fireEvent('onLayoutBack');
}

Ext.define('app.view.stage.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.stageshow',
    
	layout: {
    	type: 'hbox',
    },
    
    border: false,
    
    initComponent: function() {
    	this.items = [
    		{
    			xtype: 'image',
    			name: 'StageImage',
    			id: 'StageImage',
    			autoEl: {
                    tag: 'iframe',
                    id: 'location',
//                    src: '/TicketsOnline/svg/location/1.svg',
                    height: '1000px'
                },
    			flex: 3,
    			listeners:{
    	            'onclick':function (comp) {
    	                comp.getEl().on('click', function (el) {
    	                    this.fireEvent('onImageClick', this);
    	                }, this);
    	            }
    	        }
    		},
    		{
    			xtype: 'panel',
    			margins: {top:40, left:20, right: 20, bottom:0},
    			layout: {
    		    	type: 'vbox',
    		    },
    		    
    		    border: false,
    		    
    		    items:[
    		    	{
    	    			xtype: 'image',
    	    			src: 'img/event00001.jpg',
    	    		},
    	    		{
    	    			xtype: 'form',
    	    			margins: {top:40, left:15, right: 0, bottom:0},
    	    			border: false,
    	    			items:[
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
    	    				},
    	    				{
    	    					xtype: 'gridshow',
    	    					name: 'gridShow',
    	    					id: 'gridShow'
    	    				}
    	    			],
    	    			dockedItems: [
    	    		        {
    	    		            xtype: 'toolbar',
    	    		            dock: 'bottom',
    	    		            ui: 'footer',
    	    		            layout: {
    	    		                type: 'hbox',
    	    		                pack: 'end'
    	    		            },
    	    		            items: [
    	    		                {
    	    		                    xtype: 'button',
    	    		                    name: 'returnBtn',
    	    		                    id: 'returnBtn',
    	    		                    text: 'Regresar',
    	    		                    margins: {top:40, left:0, right: 0, bottom:0},
    	    		                },
    	    		                {
    	    		                    xtype: 'button',
    	    		                    name: 'buyBtn',
    	    		                    id: 'buyBtn',
    	    		                    text: 'Comprar',
    	    		                    disabled : true
    	    		                }
    	    		            ]
    	    		        }
    	    		    ],
    	    		}
    		    ],
    		    flex: 2
    		}
    	],
    	this.callParent(arguments);
    }
});