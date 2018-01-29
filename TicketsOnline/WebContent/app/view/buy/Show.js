Ext.define('app.view.buy.Show' ,{
    extend: 'Ext.form.Panel',
    alias: 'widget.buyshow',
    
    initComponent: function() {
    	console.log('Inicializando vista buy...');

    	this.border = false;
    	this.bodyStyle = 'padding: 50px';
    	
    	this.fieldDefaults = {
            labelAlign: 'right',
            labelWidth: 90,
            msgTarget: 'qtip'
        };
    	
        this.items = [
             // Contact info
             {
                 xtype: 'fieldset',
                 title: 'Informacion de contacto',
                 defaultType: 'textfield',
                 layout: 'anchor',
                 defaults: {
                     anchor: '100%'
                 },
                 items: [{
                     xtype: 'fieldcontainer',
                     fieldLabel: 'Nombre',
                     layout: 'hbox',
                     combineErrors: true,
                     defaultType: 'textfield',
                     defaults: {
                         hideLabel: 'true'
                     },
                     items: [{
                         name: 'firstName',
                         fieldLabel: 'Nombre',
                         flex: 2,
                         emptyText: 'Ingrese nombre',
                         allowBlank: false
                     }, {
                         name: 'lastName',
                         fieldLabel: 'Apellido',
                         flex: 3,
                         margins: '0 0 0 6',
                         emptyText: 'Apellido',
                         allowBlank: false
                     }]
                 }, {
                     xtype: 'container',
                     layout: 'hbox',
                     defaultType: 'textfield',
                     items: [{
                         fieldLabel: 'Email',
                         name: 'email',
                         vtype: 'email',
                         flex: 1,
                         allowBlank: false
                     }, {
                         fieldLabel: 'Telefono',
                         labelWidth: 100,
                         name: 'phone',
                         width: 190,
                         emptyText: 'xxx-xxx-xxxx',
                         maskRe: /[\d\-]/,
                         regex: /^\d{3}\d{3}\d{4}$/,
                         regexText: 'Debe estar en formato xxx-xxx-xxxx'
                     }]
                 }]
             },

             // Mailing Address
             {
                 xtype: 'fieldset',
                 title: 'Direccion',
                 defaultType: 'textfield',
                 layout: 'anchor',
                 defaults: {
                     anchor: '100%'
                 },
                 items: [{
                     fieldLabel: 'Direccion',
                     name: 'mailingStreet',
                     billingFieldName: 'billingStreet',
                     allowBlank: false
                 }, {
                     xtype: 'container',
                     layout: 'hbox',
                     items: [{
                         xtype: 'textfield',
                         fieldLabel: 'Ciudad',
                         name: 'mailingCity',
                         billingFieldName: 'billingCity',
                         flex: 1,
                         allowBlank: false
                     }, {
                         xtype: 'combobox',
                         name: 'mailingState',
                         billingFieldName: 'billingState',
                         fieldLabel: 'Estado',
                         labelWidth: 50,
                         width: 300,
                         store: Ext.data.StoreManager.lookup('States'),
                         valueField: 'abbr',
                         displayField: 'name',
                         typeAhead: true,
                         queryMode: 'local',
                         allowBlank: false,
                         forceSelection: true
                     }, {
                         xtype: 'textfield',
                         fieldLabel: 'Codigo Postal',
                         labelWidth: 80,
                         name: 'mailingPostalCode',
                         billingFieldName: 'billingPostalCode',
                         width: 160,
                         allowBlank: false,
                         maxLength: 10,
                         enforceMaxLength: true,
                         maskRe: /[\d\-]/,
                         regex: /^\d{5}(\-\d{4})?$/,
                         regexText: 'Debe estar en formato xxxxx o xxxxx-xxxx'
                     }]
                 }]
             },

             // Credit card info
             {
                 xtype: 'fieldset',
                 title: 'Tarjeta de credito',
                 layout: 'anchor',
                 defaults: {
                     anchor: '100%'
                 },
                 items: [{
                     xtype: 'radiogroup',
                     anchor: 'none',
                     layout: {
                         autoFlex: false
                     },
                     defaults: {
                         name: 'ccType',
                         style: 'margin-right:15px'
                     },
                     items: [{
                         inputValue: 'visa',
                         boxLabel: 'VISA',
                         checked: true
                     }, {
                         inputValue: 'mastercard',
                         boxLabel: 'MasterCard'
                     }, {
                         inputValue: 'amex',
                         boxLabel: 'American Express'
                     }, {
                         inputValue: 'discover',
                         boxLabel: 'Discover'
                     }]
                 }, {
                     xtype: 'textfield',
                     name: 'ccName',
                     fieldLabel: 'Nombre en tarjeta',
                     allowBlank: false
                 }, {
                     xtype: 'container',
                     layout: 'hbox',
                     items: [{
                         xtype: 'textfield',
                         name: 'ccNumber',
                         fieldLabel: 'Numero en tarjeta',
                         flex: 1,
                         allowBlank: false,
                         minLength: 15,
                         maxLength: 16,
                         enforceMaxLength: true,
                         maskRe: /\d/
                     }, {
                         xtype: 'fieldcontainer',
                         fieldLabel: 'Expira',
                         labelWidth: 75,
                         layout: 'hbox',
//                         width: 235,
                         items: [{
                             xtype: 'combobox',
                             name: 'ccExpireMonth',
                             displayField: 'name',
                             valueField: 'num',
                             queryMode: 'local',
                             emptyText: 'Mes',
                             hideLabel: true,
                             margins: '0 6 0 0',
                             store: Ext.data.StoreManager.lookup('Months'),
                             flex: 1,
                             allowBlank: false,
                             forceSelection: true
                         }, {
                             xtype: 'numberfield',
                             name: 'ccExpireYear',
                             hideLabel: true,
                             width: 55,
                             value: new Date().getFullYear(),
                             minValue: new Date().getFullYear(),
                             allowBlank: false
                         }]
                     }]
                 }]
             }
         ];

         this.buttons = [
        	 {
        		xtype: 'textfield',
				fieldLabel:	'Total a pagar',
				name:	'totalAmount',
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
        	 {
	             text: 'Regresar',
	             name: 'returnBtn02',
	             handler: function() {
	                 this.up('form').getForm().reset();
	             }
	         }, 
	         {
	             text: 'Pagar',
	             name: 'buyBtn02'
	         }
	         
	    ]

    	this.callParent(arguments);
    }
});

