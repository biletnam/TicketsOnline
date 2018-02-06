Ext.define('app.controller.Stage', {
    extend: 'Ext.app.Controller',

    views:[
    	'stage.Show'
    ],
    
    init: function() {
        console.log('Inicializando controlador stage...');
        this.control({
        	'button[name=returnBtn]':{
        		click: this.returnCard
        	},
        	'button[name=buyBtn]':{
        		click: this.buy
        	},
        	'image':{
        		onImageClick: this.selectSeat
        	}
        });
    },
    
    returnCard: function(button){
    	var panel01 = Ext.getCmp('bodyshow02');

    	Ext.getCmp('buyBtn').setDisabled(true);
    	panel01.layout.setActiveItem(0);
    },
    
    buy: function(button){
    	var panel01 = Ext.getCmp('bodyshow02');
    	var totalAmountCC = Ext.getCmp('buy01').query('textfield[name=totalAmount]')[0];
    	var form01 = button.getBubbleTarget().getBubbleTarget();
    	var totalAmountSt = form01.query('textfield[name=total]')[0].value;
    	
    	totalAmountCC.setValue(totalAmountSt);
    	panel01.layout.setActiveItem(2);
    },
    
    selectSeat: function(image, seat_){
    	var grid = image.getBubbleTarget().query('grid')[0];
    	var form = grid.getBubbleTarget().query('form')[0];
    	var store = grid.getStore();
    	var n = store.getCount();
    	var totalCost = 0.00;
    	var totalTax = 0.00;
    	var grandTotal = 0.00;
    	//Asiento
    	var id = seat_.id;
    	var cost = parseFloat(seat_.getAttribute('cost'));
    	
    	if (seat_){
    		
    		if (seat_.style.fill=='rgb(38, 151, 229)'){
    			store.add({seat: id, cost: cost});
         		seat_.style.fill='#ffff66'; 
         	}else if (seat_.style.fill=='rgb(255, 255, 102)'){
 				store.removeAt(store.find('seat', id));
 				seat_.style.fill='#2697e5';
         	}
    		
    		totalCost = store.sum('cost');
	    	totalTax = totalCost * .16;
	    	grandTotal = totalCost + totalTax;
	    	
	    	if (grandTotal > 0){
	    		Ext.getCmp('buyBtn').setDisabled(false);
	    	}else{
	    		Ext.getCmp('buyBtn').setDisabled(true);
	    	}
	    	
	    	form.query('textfield[name=subTotal]')[0].setValue(totalCost);
	    	form.query('textfield[name=tax]')[0].setValue(totalTax);
	    	form.query('textfield[name=total]')[0].setValue(grandTotal);
    	}
    }
});