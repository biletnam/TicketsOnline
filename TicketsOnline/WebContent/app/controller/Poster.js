Ext.define('app.controller.Poster', {
    extend: 'Ext.app.Controller',

    views:[
    	'poster.Show'
    ],
    
    init: function() {
        console.log('Inicializando controlador poster...');
        this.control({
        	'button[name=reservBtn]':{
        		click: this.openWindow
        	}
        });
    },
    
    openWindow: function(button){
    	var msg = Ext.MessageBox.wait('Por favor espere, abriendo locacion..', 'Informacion');
    	var panel01 = Ext.getCmp('bodyshow02');
    	var panel02 = Ext.getCmp('stageshow01');
    	var panel02form = panel02.down('panel');
    	
    	var dataForm = button.getBubbleTarget().getBubbleTarget(); 
    	var imagePanel = button.getBubbleTarget().getBubbleTarget().getBubbleTarget();
    	var eventId = dataForm.query('hiddenfield[name=hidEvent]')[0].getValue();
    	var stageFileName = dataForm.query('hiddenfield[name=hidStageFileName]')[0].getValue();
    	var fileName = imagePanel.query('image')[0].src;
    	var artist = dataForm.query('displayfield[name=artist]')[0].getValue();
    	var date = dataForm.query('displayfield[name=date]')[0].getValue();
    	var time = dataForm.query('displayfield[name=time]')[0].getValue();
    	var place = dataForm.query('displayfield[name=place]')[0].getValue();
    	var grid = panel02form.query('grid')[0];
    	var amountForm = grid.getBubbleTarget().query('form')[0];
    	
    	//Stage
    	panel02.query('image')[0].setSrc(stageFileName);
    	panel02form.query('image')[0].setSrc(fileName);
    	panel02form.query('displayfield[name=artist]')[0].setValue(artist);
    	panel02form.query('displayfield[name=date]')[0].setValue(date);
    	panel02form.query('displayfield[name=time]')[0].setValue(time);
    	panel02form.query('displayfield[name=place]')[0].setValue(place);
    	
    	grid.getStore().removeAll();
    	grid.getStore().sync();
    	
    	amountForm.query('textfield[name=subTotal]')[0].setValue(0.00);
    	amountForm.query('textfield[name=tax]')[0].setValue(0.00);
    	amountForm.query('textfield[name=total]')[0].setValue(0.00);

    	panel01.layout.setActiveItem(1);
    	msg.close();
    }
});