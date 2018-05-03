function actionSeat(seat_){
	var img = Ext.getCmp('seatsLayout');
 	img.fireEvent('onseatsLayoutClick', img, seat_);
}

function actionSeat2(seat_){
	var img = Ext.getCmp('seatsLayout');
 	img.fireEvent('onseatsLayoutClick2', img, seat_);
}

function back(layout_){
	window.history.back();
	Ext.getCmp('seatsLayout').fireEvent('onLayoutBack');
}

Ext.define('app.view.seats.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.seatsshow',
	layout: {
    	type: 'vbox',
    	align: 'stretch'
    },
    border: false,
    initComponent: function() {
    	this.items = [
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
	                }
	            ]
	        },
    		{
    			xtype: 'image',
    			name: 'seatsLayout',
    			id: 'seatsLayout',
    			autoEl: {
                    tag: 'iframe',
                    id: 'location',
                    name: 'location',
                    src: 'Accion2/Layout?location='+window.parent.eventId+'&companyId='+window.parent.companyId			//CSAXXX
                },
    			listeners:{
    				load:{
    					element: 'el',
    					fn: function(element, frame){
    						var altura = element.target.contentWindow.document.getElementsByTagName('svg')[0].height.baseVal.value + 50; 
    						//Seat frame adjust
    						element.target.style.height = altura + 'px';
    						Ext.getCmp('seatsshow').doLayout();
    						var iOS = !!navigator.platform && /iPad|iPhone|iPod/.test(navigator.platform);
    						
    						if (iOS){
    							element.target.style.transform="scale(.5)";
    						}
    						
    				        window.top.resizeIframe(window.top.document.getElementById('frame'));
    					}
    				},
    	            'onclick':function (comp) {
    	                comp.getEl().on('click', function (el) {
    	                    this.fireEvent('onseatsLayoutClick', this);
    	                }, this);
    	            }
    	        }
    		},
    		{
    			xtype: 'panel',
    		    border: false,
    		    margins: {top:10, left:0, right: 0, bottom:0},
    		    items:[
    		    	{
    		    		xtype: 'panel',
    		    		layout: {
    	    		    	type: 'hbox',
    	    		    },
    	    		    border: false,
    	    		    items:
    	    		    [
    	    		    	{
    	    		    		xtype: 'panel',
    	    		    		border: false,
    	    		    		flex: 1
    	    		    	},
    	    		    	{
		    		    		xtype: 'grid',
		    	    			name: 'seatsGrid',
		    	    			id: 'seatsGrid',
		    	    			cls: 'custom-grid', 
		    	    			border : true,
		    	    			store: Ext.data.StoreManager.lookup('Grid'),
		    	    			flex: 3,
		    	    	        columns: [
		    	    	        	{
		    	    	        		text: 'Asiento',
		    	    	        		dataIndex: 'seat',
		    	    	        		flex: 2
		    	    	        	},
		    	    	        	{
		    	    	        		xtype: 'numbercolumn',
		    	    	        		text: 'Costo',
		    	    	                dataIndex: 'cost',
		    	    	                maskRe: /[0-9\$\.]/,
		    	    	                flex: 1,
		    	    	                align:'right', 
		    	    	                style: 'text-align:left',
		    	    	        	},
		    	    	        	{
		    	    	        		xtype: 'numbercolumn',
		    	    	        		text: 'Comision',
		    	    	                dataIndex: 'commision',
		    	    	                maskRe: /[0-9\$\.]/,
		    	    	                flex: 1,
		    	    	                align:'right', 
		    	    	                style: 'text-align:left',
		    	    	        	},
		    	    	        	{
		    	    	        		xtype: 'numbercolumn',
		    	    	        		text: 'Subtotal',
		    	    	                dataIndex: 'subTotal',
		    	    	                maskRe: /[0-9\$\.]/,
		    	    	                flex: 1,
		    	    	                align:'right', 
		    	    	                style: 'text-align:left',
		    	    	        	}
		    	    	        ] 
    	    		    	},
    	    		    	{
    	    		    		xtype: 'panel',
    	    		    		border: false,
    	    		    		flex: 1
    	    		    	},
    	    		    ]
    	    		},
    	    		{
    	    			xtype: 'panel',
    	    			border: false,
    	    			layout: {
    	    		    	type: 'hbox',
    	    		    },
    	    		    items:
    	    		    [
    	    		    	{
    	    		    		xtype: 'panel',
    	    		    		flex: 1,
    	    		    		border: false,
    	    		    	},
    	    		    	{
    	    					xtype: 'form',
    	    					flex: 3,
    	    					border: false,
    	    					defaultType: 'textfield',
    	    					defaults:{
    	    						anchor:	'-0'
    	    					},
    	    					margins: {top:25, left:0, right: 0, bottom:0},
    	    					items: [
//    	    						{
//    	    							fieldLabel:	'Subtotal',
//    	    							name:	'subTotal',
//    	    							id:	'subTotal',
//    	    							maskRe: /[0-9\$\.]/,
//    	    							value: '0.00',
//    	    							readOnly:	true,
//    	    							fieldStyle: 'text-align: right;',
////    	    							fieldCls:'size18',
//    	    							listeners: {
//    	    								change: function(object, newValue, oldValue){
//    	    									newValue = Ext.util.Format.usMoney(newValue.replace(/[^0-9\.]/g, ''));
//    	    									if(newValue == '$NaN.00') object.setValue(oldValue); else object.setValue(newValue);
//    	    								}
//    	    							}
//    	    						},
//    	    						{
//    	    							fieldLabel:	'Impuestos',
//    	    							name:	'tax',
//    	    							id:	'tax',
//    	    							maskRe: /[0-9\$\.]/,
//    	    							value: '0.00',
//    	    							readOnly:	true,
//    	    							fieldStyle: 'text-align: right;',
////    	    							fieldCls:'size18',
//    	    							listeners: {
//    	    								change: function(object, newValue, oldValue){
//    	    									newValue = Ext.util.Format.usMoney(newValue.replace(/[^0-9\.]/g, ''));
//    	    									if(newValue == '$NaN.00') object.setValue(oldValue); else object.setValue(newValue);
//    	    								}
//    	    							}
//    	    						},
    	    						{
    	    							fieldLabel:	'Total',
    	    							name:	'total',
    	    							id:	'total',
    	    							maskRe: /[0-9\$\.]/,
    	    							value: '0.00',
    	    							readOnly:	true,
    	    							fieldStyle: 'text-align: right;',
//    	    							fieldCls:'size18b',
    	    							listeners: {
    	    								change: function(object, newValue, oldValue){
    	    									newValue = Ext.util.Format.usMoney(newValue.replace(/[^0-9\.]/g, ''));
    	    									if(newValue == '$NaN.00') object.setValue(oldValue); else object.setValue(newValue);
    	    								}
    	    							}
    	    						},
    	    					]
    	    		    	},
    	    		    	{
    	    		    		xtype: 'panel',
    	    		    		flex: 1,
    	    		    		border: false,
    	    		    	},
    	    		    ]
    	    		}
    		    ],
    		}
    	],
    	this.callParent(arguments);
    }
});