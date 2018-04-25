Ext.require([
	'Ext.window.*'
]);

function showTicketReceipt(url_){
	var w = Ext.getCmp('windowTicketReceipt');
	var html = '<iframe src="'+url_+'" width="450" height="800"/>';
		
	w.html = html;
	w.show();
}

Ext.onReady(function() {
   
    var windowT = Ext.widget('window',{
    	title: 'Comprobante de pago enviado a su email',
    	id: 'windowTicketReceipt',
    	name: 'windowTicketReceipt',
    	modal:	true,
    	closable: true,
    	listeners:{
            'close':function(win){
            	window.location.href = '../index.jsp';
             }
    	}
    });
    
});