var i = 0;

Ext.define('app.controller.Seats', {
    extend: 'Ext.app.Controller',

    views:[
    	'seats.Show'
    ],
    
    init: function() {
        console.log('Inicializando controlador seats...');
        this.control({
        	'image[name=seatsLayout]':{
        		resize: function(){
        			i++;
        			
        			if (i > 1 && window.parent.dialog){
        				window.parent.dialog.hide();
        			}
        		}
        	},
        	'button[name=returnBtn]':{
        		click: this.onLayoutBack2
        	},
        	'image':{
        		onseatsLayoutClick: this.selectSeat,
        		onseatsLayoutClick2: this.selectSeat2,
        		onLayoutBack: this.onLayoutBack,
        		onseatsLayoutLoad: this.onseatsLayoutLoad
        	},
        });
    },
    
    returnCard: function(button){
    	var panel01 = Ext.getCmp('bodyshow02');

    	panel01.layout.setActiveItem(0);
    },
    
    buy: function(button){
    	var store = Ext.getCmp('seatsGrid').getStore();
    	var seats = [];
    	var i = 0;

    	store.each(function(record,id){
    	    seats[i++] = record.get('seat');
    	});
    	
    	Ext.Ajax.request({
			url: 'Accion/SeatValidation',
			method: 'POST',
			params:{
				seats: seats
			},
			scope: this,
			success: this.jsSuccess,
			failure: this.jsError
		})
    },
    
    jsSuccess: function(respuesta, opts){
    	window.open('https://www.paypal.com');
	},
	
	jsError(respuesta){
		Ext.MessageBox.alert('Fallo','Fallo');
	},
    
    selectSeat: function(image, seat_){
    	var grid = image.getBubbleTarget().query('grid')[0];
    	var form = grid.getBubbleTarget().getBubbleTarget().query('form')[0];
    	var store = grid.getStore();
    	var n = store.getCount();
    	var totalCost = 0.00;
    	var totalTax = 0.00;
    	var grandTotal = 0.00;
    	var comissionTotal = 0.00;
    	var id = seat_.id;
    	var cost = parseFloat(seat_.getAttribute('cost'));
    	var commision = parseFloat(seat_.getAttribute('commision'));
    	var subTotal = parseFloat(seat_.getAttribute('subtotal'));
    	var sectionId = seat_.getAttribute('sectionid');
    	var index = parseInt(seat_.getAttribute('index'));
    	var number = seat_.getAttribute('number');
    	var eventId = seat_.getAttribute('eventid');
    	var location = seat_.getAttribute('location');
    	var totalMasComision = 0;
    	var cadena = '';
    	
    	//Validate seat status
    	var dialogo = Ext.MessageBox.wait('Espere por favor, validando asiento', 'Informacion');
    	
    	Ext.Ajax.request({
    		url: 'Accion/confirmSeat',
    		params:{
    			seatId: id,
    			eventId: eventId,
    			sectionId: sectionId,
    			index: index,
    			number: number,
    			location: location
    		},
    		method: 'POST',
   	        success: function(response, opts) {
   	        	dialogo.hide();
   				result = JSON.parse(response.responseText);
   				mensaje = result.msg;
   				res = result.success;
   				if (!res){
   					Ext.MessageBox.alert('Informacion','Estimado cliente, este asiento se encuentra en proceso de compra, por lo cual no es posible seleccionarlo');
   				}else{
   					if (seat_){
   			    		if (seat_.style.fill=='rgb(38, 151, 229)'){
   			    			store.add({seat: location + ' ' + number, cost: cost, commision: commision, subTotal: subTotal});
   			         		seat_.style.fill='#ffff66';
   			         		cadena = ' |Seccion ' + sectionId + ', Butaca ' + number + "| ";
				    		top.payment.seats[top.payment.seats.length] = cadena;
   			         	}else if (seat_.style.fill=='rgb(255, 255, 102)'){
   			 				store.removeAt(store.find('seat', location + ' ' + number));
   			 				seat_.style.fill='#2697e5';
   			 				cadena = sectionId + ':' + number;
   			 				var index = top.payment.seats.indexOf(' |Seccion ' + sectionId + ', Butaca ' + number + '| ');
   			 				if (index > -1){
   			 					top.payment.seats.splice(index, 1);
   			 				}
   			 				console.log(top.payment.seats);
   			         	}
   			    		
   				    	grandTotal = store.sum('subTotal');
   				    	comissionTotal = store.sum('commision');
   				    	
   				    	try{
   				    		totalMasComision = (grandTotal + comissionTotal) * 1.06;
   				    		top.payment.amount = totalMasComision.toFixed(2);
   				    	}catch(e){
   				    		console.error(e);
   				    		console.error('Error en calculo de total');
   				    		top.payment.amount = 0;
   				    	}
   				    	
   				    	form.query('textfield[name=total]')[0].setValue(grandTotal);
   			    	}
   			    	window.parent.resizeIframe(window.parent.document.getElementById('frame'));
   			    	window.top.document.getElementById('minute-counter').contentWindow.update(grandTotal);
   				}
	         },
	         failure: function(response, opts){
	        	dialogo.hide();
				result = JSON.parse(response.responseText);
   				console.log(mensaje);
				mensaje = result.msg;
				Ext.MessageBox.alert('Informacion','Estimado cliente, este asiento se encuentra en proceso de compra, por lo cual no es posible seleccionarlo');
			 }
   	     })
    },
    
    selectSeat2: function(image, seat_){
    	var grid = image.getBubbleTarget().query('grid')[0];
    	var form = grid.getBubbleTarget().getBubbleTarget().query('form')[0];
    	var store = grid.getStore();
    	var n = store.getCount();
    	var totalCost = 0.00;
    	var totalTax = 0.00;
    	var grandTotal = 0.00;
    	var comissionTotal = 0.00;
    	var id = seat_.id;
    	var cost = parseFloat(seat_.getAttribute('cost'));
    	var commision = parseFloat(seat_.getAttribute('commision'));
    	var subTotal = parseFloat(seat_.getAttribute('subtotal'));
    	var sectionId = seat_.getAttribute('sectionid');
    	var index = parseInt(seat_.getAttribute('index'));
    	var number = seat_.getAttribute('number');
    	var eventId = seat_.getAttribute('eventid');
    	var location = seat_.getAttribute('location');
    	var totalMasComision = 0;
    	
    	//Validate seat status
    	var dialogo = Ext.MessageBox.wait('Espere por favor, validando asiento', 'Informacion');
    	
    	Ext.Ajax.request({
    		url: 'Accion/confirmSeat',
    		params:{
    			seatId: id,
    			eventId: eventId,
    			sectionId: sectionId,
    			index: index,
    			number: number,
    			location: location
    		},
    		method: 'POST',
   	        success: function(response, opts) {
   	        	dialogo.hide();
   				result = JSON.parse(response.responseText);
   				mensaje = result.msg;
   				res = result.success;
   				if (!res){
   					Ext.MessageBox.alert('Informacion','Estimado cliente, este asiento se encuentra en proceso de compra, por lo cual no es posible seleccionarlo');
   				}else{
   					if (seat_){
  			    		store.add({seat: location + ' ' + number, cost: cost, commision: commision, subTotal: subTotal});
   				    	grandTotal = store.sum('subTotal');
   				    	comissionTotal = store.sum('commision');
   				    	
   				    	try{
   				    		totalMasComision = (grandTotal + comissionTotal) * 1.06;
   				    		top.payment.amount = totalMasComision.toFixed(2);
   				    	}catch(e){
   				    		console.error('Error en calculo de total');
   				    		top.payment.amount = 0;
   				    	}
   				    	
   				    	form.query('textfield[name=total]')[0].setValue(grandTotal);
   			    	}
   			    	window.parent.resizeIframe(window.parent.document.getElementById('frame'));
   			    	window.top.document.getElementById('minute-counter').contentWindow.update(grandTotal);
   				}
	         },
	         failure: function(response, opts){
	        	dialogo.hide();
				result = JSON.parse(response.responseText);
   				console.log(mensaje);
				mensaje = result.msg;
				Ext.MessageBox.alert('Informacion','Estimado cliente, este asiento se encuentra en proceso de compra, por lo cual no es posible seleccionarlo');
			 }
   	     })
    },
    
    onLayoutBack: function(){
    	Ext.data.StoreManager.lookup('Grid').removeAll();
    	Ext.getCmp('total').setValue(0);
    	top.payment.amount = 0;
    	window.top.document.getElementById('minute-counter').contentWindow.update(0);
    },
    
    onLayoutBack2: function(){
    	this.onLayoutBack();
    	window.parent.location.href = 'index.html';
    	window.parent.resizeIframe(window.parent.document.getElementById('frame'));
    	Ext.Ajax.request({
    		url: 'Accion/releaseAllSeat',
    		method: 'POST'
   	     })
    },
    
    onseatsLayoutLoad: function(obj){
    	console.log(obj);
    }
    
});