Ext.define('app.controller.Body', {
    extend: 'Ext.app.Controller',

    views:[
    	'body.Show'
    ],
    
    init: function() {
    	this.control({
    		'bodyshow':{
    			render: function(){
    				var body = Ext.getCmp('bodyshow01');
    				var poster = null;
    				
    				for (i = 0; i < 40; i++){
    					poster = Ext.create('app.view.poster.Show');
    					poster.query('hiddenfield[name=hidEvent]')[0].setValue(i);
    					poster.query('hiddenfield[name=hidStageFileName]')[0].setValue('svg/location/1.svg');
    					poster.query('displayfield[name=artist]')[0].setValue('Chuy Lizarraga'+i);
    					poster.query('displayfield[name=date]')[0].setValue('2018-02-09');
    					poster.query('displayfield[name=time]')[0].setValue('20:00 hrs.');
    					poster.query('displayfield[name=place]')[0].setValue('A mi Hacienda de Pico Rivera'+i);
    					body.add(poster);
    				}
    				body.doLayout();
    			}
    		} 
    	})
    }
});