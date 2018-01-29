Ext.define('app.view.grid.Show' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.gridshow',
    
    initComponent: function() {
    	console.log('Inicializando vista grid...');
    	this.layout = {type: 'vbox'};
    	this.border = false,
    	this.items = [
    		{
    			xtype: 'grid',
    			margins: {top:40, left:0, right: 0, bottom:0},
    			border : false,
    			store: Ext.data.StoreManager.lookup('Grid'),
    	        columns: [
    	        	{
    	        		text: 'Asiento',
    	        		dataIndex: 'seat',
    	        		width: 148
    	        	},
    	        	{
    	        		xtype: 'numbercolumn',
    	        		text: 'Costo en $',
    	                dataIndex: 'cost',
    	                maskRe: /[0-9\$\.]/,
    	                width: 148,
    	                align:'right', 
    	                style: 'text-align:left',
    	        	}
    	        ]
    		},
    		{
				xtype: 'form',
				border: false,
				width: 300,
				layout: {
					type: 'vbox',
			        align: 'stretch'
			    },
				defaultType:	'textfield',
				defaults: {
					labelWidth: 60,
			        labelAlign: 'left',
			        margin: '10 2 2 2'
			    },
				items: [
					{
						fieldLabel:	'Subtotal',
						name:	'subTotal',
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