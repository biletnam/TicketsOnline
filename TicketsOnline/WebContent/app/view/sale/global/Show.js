Ext.define('app.view.sale.global.Show', {
    extend: 'Ext.form.Panel',
    alias:'widget.saleglobalshow',

	title: 'Venta Global',
	width: '100%',
	height: 600,
    layout: {
        type: 'anchor',
        reserveScrollbar: true 
    },
    autoScroll: true,
	
	items:[
    	{
    		xtype: 'grid',
    		name: 'grid01',
    		id: 'grid01',
    		store: 'GlobalSale',
    		anchor: '100%',
			features: [{
			    ftype: 'summary',
		    }],
    		columns:
    			[
    				{
    					header: 'Localidad',
    					dataIndex: 'localidad',
    					flex: 1
    				},
    				{
    					header: 'Estatus',
    					dataIndex: 'status'
    				},
    				{
    					header: 'Boletos',
    					dataIndex: 'nBoletos',
    					summaryType: 'sum',
    		            summaryRenderer: function(value, summaryData, dataIndex) {
    		                return value;
    		            }
    				},
    				{
    					xtype: 'numbercolumn',
    					header: 'Precio',
    					dataIndex: 'precio',
    					maskRe: /[0-9\$\.]/,
    	                align:'right', 
    	                style: 'text-align:left',
    				},
    				{
    					xtype: 'numbercolumn',
    					header: '%Dcto.',
    					dataIndex: 'porDcto',
    					maskRe: /[0-9\$\.]/,
    	                align:'right', 
    	                style: 'text-align:left',
    				},
    				{
    					xtype: 'numbercolumn',
    					header: 'Importe Bruto',
    					dataIndex: 'impBruto',
    					width: 150,
    					maskRe: /[0-9\$\.]/,
    	                align:'right', 
    	                style: 'text-align:left',
    	                summaryType: 'sum',
    		            summaryRenderer: function(value, summaryData, dataIndex) {
    		                return Ext.util.Format.usMoney(value);
    		            }
    				}
    			]
    	}
    ],
    dockedItems:[
    	{
	        xtype:'toolbar',
	        dock:'top',
	        items:
	        [
	 	    	{
	 	    		xtype: 'datefield',
	 	    		fieldLabel: 'Hasta',
	 				name: 'creationDateF',
	 				id: 'creationDateF',
	 				format: 'd/M/Y',
	 				value: new Date(),
	 				readOnly: true
	 	    	},
		        '->',
		        {
		            id:'queryBnt', 
		            name: 'queryBnt',
		            type:'button',
		            text:'Consultar',
		            width: 70
		        },
		        {
		            id:'pdfBnt',
		            name: 'pdfBnt',
		            type:'button',
		            text:'PDF',
		            width: 70
		        },
		        {
		            id:'excelBnt',
		            name: 'excelBnt',
		            type:'button',
		            text:'Excel',
		            width: 70
		        }
			]
    	}
    ],
	
	initComponent: function(){
		this.callParent(arguments);
	}
});
