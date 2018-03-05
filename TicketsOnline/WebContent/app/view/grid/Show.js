Ext.define('app.view.grid.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.gridshow',
    
    initComponent: function() {
    	console.log('Inicializando vista grid...');
    	this.layout = 
    		{
    			type: 'vbox',
    			align: 'stretch',
    	        pack: 'start',
    		};
    	this.border = false,
    	this.items = [
    		{
    			xtype: 'grid',
    			name: 'seatGrid',
    			id: 'seatGrid',
    			margins: {top:0, left:0, right: 0, bottom:0},
    			border : false,
    			store: Ext.data.StoreManager.lookup('Grid'),
    	        columns: [
    	        	{
    	        		text: 'Asiento',
    	        		dataIndex: 'seat',
    	        		flex: 1
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
    	                dataIndex: 'sutTotal',
    	                maskRe: /[0-9\$\.]/,
    	                flex: 1,
    	                align:'right', 
    	                style: 'text-align:left',
    	        	}
    	        ]
    		},
    		{
				xtype: 'form',
				border: true,
				layout: {
					type: 'anchor',
			        pack: 'right',
			    },
				defaultType:	'textfield',
//				defaults: {
//					labelWidth: 60,
//			        margin: '10 2 2 2'
//			    },
				items: [
					{
						fieldLabel:	'Subtotal',
						name:	'subTotal',
						id:	'subTotal',
						maskRe: /[0-9\$\.]/,
						value: '0.00',
						readOnly:	true,
						fieldStyle: 'text-align: right;',
						fieldCls:'size18',
						listeners: {
							change: function(object, newValue, oldValue){
								newValue = Ext.util.Format.usMoney(newValue.replace(/[^0-9\.]/g, ''));
								if(newValue == '$NaN.00') object.setValue(oldValue); else object.setValue(newValue);
							}
						}
					},
					{
						fieldLabel:	'Impuestos',
						name:	'tax',
						id:	'tax',
						maskRe: /[0-9\$\.]/,
						value: '0.00',
						readOnly:	true,
						fieldStyle: 'text-align: right;',
						fieldCls:'size18',
						listeners: {
							change: function(object, newValue, oldValue){
								newValue = Ext.util.Format.usMoney(newValue.replace(/[^0-9\.]/g, ''));
								if(newValue == '$NaN.00') object.setValue(oldValue); else object.setValue(newValue);
							}
						}
					},
					{
						fieldLabel:	'Total',
						name:	'total',
						id:	'total',
						maskRe: /[0-9\$\.]/,
						value: '0.00',
						readOnly:	true,
						fieldStyle: 'text-align: right;',
						fieldCls:'size18b',
						listeners: {
							change: function(object, newValue, oldValue){
								newValue = Ext.util.Format.usMoney(newValue.replace(/[^0-9\.]/g, ''));
								if(newValue == '$NaN.00') object.setValue(oldValue); else object.setValue(newValue);
							}
						}
					},
				]
    		}
    	]
    	
    	this.callParent(arguments);
    }
});