Ext.define('app.store.GlobalSale', {
    extend: 'Ext.data.Store',
    
    model: 'app.model.GlobalSale',
     
    proxy: {
        type: 'ajax',
        api: {
            read: 'Accion/getGlobalSale',
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message'
        },
        listeners: {
        	exception: function(proxy, response, operation){
        		var json = Ext.decode(response.responseText);
        		if (json){
                    Ext.MessageBox.show({
                        title: 'Error',
                        msg: json.msg,
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
        		}else{
        			Ext.MessageBox.show({
                        title: 'Error',
                        msg: 'Error General',
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
        		}
            }
        }
    }
});