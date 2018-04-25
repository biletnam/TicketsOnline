Ext.define('app.controller.GlobalSale', {
    extend: 'Ext.app.Controller',
    
    stores: [
    	'GlobalSale'
    ],
    
    views:['sale.global.Show'],
    
    init: function(){
    	this.control({
			 'button[name=queryBnt]':{
				 click: this.queryMovements
			 },
			 'button[name=pdfBnt]':{
				 click: this.pdfMovements
			 },
			 'button[name=excelBnt]':{
				 click: this.xlsMovements
			 }
    	})
    },
    
    queryMovements: function(btn){
    	Ext.getCmp('grid01').store.load();
	},
	
	pdfMovements: function(btn){
//		window.open('Reporte?nombre=GlobalSale&@EventoPkId=2&@FechaInicial=2018-01-01&@FechaFinal=2020-04-08&@Todo=false&@CajeroPkId=2&tipo=pdf'); //CSAXXX
		window.open('Reporte?nombre=GlobalSale&@EventoPkId=237&@FechaInicial=2018-01-01&@FechaFinal=2020-04-08&@Todo=false&@CajeroPkId=2&tipo=pdf'); //CSAXXX
	},
	
	xlsMovements: function(btn){
//		window.open('Reporte?nombre=GlobalSale&@EventoPkId=2&@FechaInicial=2018-01-01&@FechaFinal=2020-04-08&@Todo=false&@CajeroPkId=2&tipo=vnd.ms-excel'); //CSAXXX
		window.open('Reporte?nombre=GlobalSale&@EventoPkId=237&@FechaInicial=2018-01-01&@FechaFinal=2020-04-08&@Todo=false&@CajeroPkId=2&tipo=vnd.ms-excel'); //CSAXXX
	}
});