Ext.define('app.model.GlobalSale', {
    extend: 'Ext.data.Model',
    fields: [
    	{name: 'localidad', mapping: 3, type: 'string'},
    	{name: 'nBoletos', mapping: 4, type: 'int'},
		{name: 'precio', mapping: 5, type: 'float'},
		{name: 'porDcto', mapping: 7, type: 'float'},
		{name: 'impBruto', mapping: 6, type: 'float'},
		{name: 'status', mapping: 13, type: 'string'},
    ]
});
