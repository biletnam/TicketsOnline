Ext.require([
    'Ext.window.MessageBox',
    'Ext.tip.*'
]);

function showRegister(){
	var fL = Ext.getCmp('formPanelLogin');
	var fR = Ext.getCmp('formPanelRegister');
	var w = Ext.getCmp('window');
	
	fL.hide();
	w.setTitle('Registro de nuevo usuario');
	fR.show();
}

function showLogin(){
	var fL = Ext.getCmp('formPanelLogin');
	var fR = Ext.getCmp('formPanelRegister');
	var w = Ext.getCmp('window');
	
	fR.hide();
	w.setTitle('Iniciar sesi&oacute;n');
	fL.show();
}

Ext.require([
	'Ext.window.Window',
    'Ext.form.*',
    'Ext.Img',
    'Ext.tip.QuickTipManager'
]);

Ext.onReady(function() {
    Ext.tip.QuickTipManager.init();
   
    var formPanelRegister = Ext.widget('form', {
    	id: 'formPanelRegister',
    	name: 'formPanelRegister',
    	hidden: true,
        frame: true,
        width: 500,
        bodyPadding: 10,
        bodyBorder: false,

        defaults: {
            anchor: '100%'
        },
        fieldDefaults: {
            labelAlign: 'left',
            msgTarget: 'none',
            invalidCls: '' //unset the invalidCls so individual fields do not get styled as invalid
        },

        /*
         * Listen for validity change on the entire form and update the combined error icon
         */
        listeners: {
            fieldvaliditychange: function() {
                this.updateErrorState();
            },
            fielderrorchange: function() {
                this.updateErrorState();
            }
        },

        updateErrorState: function() {
            var me = this,
                errorCmp, fields, errors;

            if (me.hasBeenDirty || me.getForm().isDirty()) { //prevents showing global error when form first loads
                errorCmp = me.down('#formErrorState');
                fields = me.getForm().getFields();
                errors = [];
                fields.each(function(field) {
                    Ext.Array.forEach(field.getErrors(), function(error) {
                        errors.push({name: field.getFieldLabel(), error: error});
                    });
                });
                errorCmp.setErrors(errors);
                me.hasBeenDirty = true;
            }
        },

        items: [{
            xtype: 'textfield',
            name: 'name',
            fieldLabel: 'Nombre completo',
            allowBlank: false,
            minLength: 6
        }, {
            xtype: 'textfield',
            name: 'email',
            fieldLabel: 'Email',
            vtype: 'email',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'address',
            fieldLabel: 'Direcci&oacute;n',
            allowBlank: false,
            minLength: 6
        },{
            xtype: 'textfield',
            name: 'phone',
            fieldLabel: 'Tel&eacute;fono',
            allowBlank: false,
            minLength: 6
        },  {
            xtype: 'textfield',
            name: 'password1',
            fieldLabel: 'Contrase&ntilde;a',
            inputType: 'password',
            style: 'margin-top:15px',
            allowBlank: false,
            minLength: 1
        }, {
            xtype: 'textfield',
            name: 'password2',
            fieldLabel: 'Confirmar contrase&ntilde;a',
            inputType: 'password',
            allowBlank: false,
            /**
             * Custom validator implementation - checks that the value matches what was entered into
             * the password1 field.
             */
            validator: function(value) {
                var password1 = this.previousSibling('[name=password1]');
                return (value === password1.getValue()) ? true : 'No coinciden las contrase&ntilde;as.'
            }
        },

        /*
         * Terms of Use acceptance checkbox. Two things are special about this:
         * 1) The boxLabel contains a HTML link to the Terms of Use page; a special click listener opens this
         *    page in a modal Ext window for convenient viewing, and the Decline and Accept buttons in the window
         *    update the checkbox's state automatically.
         * 2) This checkbox is required, i.e. the form will not be able to be submitted unless the user has
         *    checked the box. Ext does not have this type of validation built in for checkboxes, so we add a
         *    custom getErrors method implementation.
         */
        {
            xtype: 'checkboxfield',
            name: 'acceptTerms',
            fieldLabel: 'Condiciones de uso',
            hideLabel: true,
            style: 'margin-top:15px',
            boxLabel: 'He leido y aceptado las <a href="#" class="terms">condiciones de uso (dar click aqu&iacute;)</a>.',

            // Listener to open the Terms of Use page link in a modal window
            listeners: {
                click: {
                    element: 'boxLabelEl',
                    fn: function(e) {
                        var target = e.getTarget('.terms'),
                            win;
                        
                        e.preventDefault();
                        
                        if (target) {
                            win = Ext.widget('window', {
                                title: 'Condicines de Uso',
                                modal: true,
                                html: Ext.getDom('legalese').innerHTML,
                                width: 900,
                                height: 500,
                                bodyStyle: 'padding: 10px 20px;',
                                autoScroll: true,
                                
                                buttons: [{
                                    text: 'Rechazar',
                                    handler: function() {
                                        this.up('window').close();
                                        formPanelRegister.down('[name=acceptTerms]').setValue(false);
                                    }
                                }, {
                                    text: 'Aceptar',
                                    handler: function() {
                                        this.up('window').close();
                                        formPanelRegister.down('[name=acceptTerms]').setValue(true);
                                    }
                                }]
                            });
                            win.show();
                        }
                    }
                }
            },

            // Custom validation logic - requires the checkbox to be checked
            getErrors: function() {
                return this.getValue() ? [] : ['Debes aceptar las condiciones de uso']
            }
        }],

        dockedItems: [{
            xtype: 'container',
            dock: 'bottom',
            layout: {
                type: 'hbox',
                align: 'middle'
            },
            padding: '10 10 5',

            items: [{
                xtype: 'component',
                id: 'formErrorState',
                baseCls: 'form-error-state',
                flex: 1,
                validText: 'V&aacute;lido',
                invalidText: 'Inv&aacute;lido',
                tipTpl: Ext.create('Ext.XTemplate', '<ul><tpl for="."><li><span class="field-name">{name}</span>: <span class="error">{error}</span></li></tpl></ul>'),

                getTip: function() {
                    var tip = this.tip;
                    if (!tip) {
                        tip = this.tip = Ext.widget('tooltip', {
                            target: this.el,
                            title: 'Error Details:',
                            autoHide: false,
                            anchor: 'top',
                            mouseOffset: [-11, -2],
                            closable: true,
                            constrainPosition: false,
                            cls: 'errors-tip'
                        });
                        tip.show();
                    }
                    return tip;
                },

                setErrors: function(errors) {
                    var me = this,
                        baseCls = me.baseCls,
                        tip = me.getTip();

                    errors = Ext.Array.from(errors);

                    // Update CSS class and tooltip content
                    if (errors.length) {
                        me.addCls(baseCls + '-invalid');
                        me.removeCls(baseCls + '-valid');
                        me.update(me.invalidText);
                        tip.setDisabled(false);
                        tip.update(me.tipTpl.apply(errors));
                    } else {
                        me.addCls(baseCls + '-valid');
                        me.removeCls(baseCls + '-invalid');
                        me.update(me.validText);
                        tip.setDisabled(true);
                        tip.hide();
                    }
                }
            }, {
                xtype: 'button',
                disabled: false,
                text: 'Regresar',
                width: 100,
                handler: function() {
                	showLogin();
                }
            },{
                xtype: 'button',
                formBind: true,
                disabled: true,
                text: 'Registrar',
                width: 100,
                handler: function() {
                    var form = this.up('form').getForm();

                    if (form.isValid()) {
                    	form.submit({
                    		clientValidation: true,
                    		url: '../Accion/addUser',
                    		success: function(response, opts) {
                    			Ext.MessageBox.alert('Informaci&oacute;n', 'Usuario dado de alta satisfactoriamente, ya puede iniciar sesi&oacute;n.');
                    			showLogin();
                    		},
                    		failure: function(form, action) {
                    			Ext.MessageBox.alert('Informaci&oacute;n', 'No se pudo dar de alta el usuario, ya existe uno con ese correo.');
                    		}
                    	});
                    }
                    
                }
            }]
        }]
    });
    
    var formPanelLogin = Ext.widget('form', {
    	id: 'formPanelLogin',
    	name: 'formPanelLogin',
        frame: true,
        width: 500,
        bodyPadding: 10,

        defaults: {
            anchor: '100%'
        },
        fieldDefaults: {
            labelAlign: 'left',
            msgTarget: 'none',
            invalidCls: '' //unset the invalidCls so individual fields do not get styled as invalid
        },

        /*
         * Listen for validity change on the entire form and update the combined error icon
         */
        listeners: {
            fieldvaliditychange: function() {
                this.updateErrorState();
            },
            fielderrorchange: function() {
                this.updateErrorState();
            }
        },

        updateErrorState: function() {
            var me = this,
                errorCmp, fields, errors;

            if (me.hasBeenDirty || me.getForm().isDirty()) { //prevents showing global error when form first loads
                errorCmp = me.down('#formErrorState2');
                fields = me.getForm().getFields();
                errors = [];
                fields.each(function(field) {
                    Ext.Array.forEach(field.getErrors(), function(error) {
                        errors.push({name: field.getFieldLabel(), error: error});
                    });
                });
                errorCmp.setErrors(errors);
                me.hasBeenDirty = true;
            }
        },
        items: [
        	{
	            xtype: 'textfield',
	            name: 'email',
	            fieldLabel: 'Email',
	            vtype: 'email',
	            allowBlank: false
	        }, {
	            xtype: 'textfield',
	            name: 'password1',
	            fieldLabel: 'Contrase&ntilde;a',
	            inputType: 'password',
	            style: 'margin-top:15px',
	            allowBlank: false,
	            minLength: 1
	        },{
		        xtype: 'label',
	            name: 'texto',
	            html: 'Reg&iacute;strate en XPTickets, es necesario para iniciar con tu compra en linea, adem&aacute;s podr&aacute;s recibir promociones exclusivas.<br>Aun no estoy registrado, <a href="#"  onclick="showRegister();">Reg&iacute;strate (dar click aqu&iacute;)</a>.<br><br>**Recuerde tener las ventanas emergentes (popups) encendidas.',
	        }
        ],
        dockedItems: [{
            xtype: 'container',
            dock: 'bottom',
            layout: {
                type: 'hbox',
                align: 'middle'
            },
            padding: '10 10 5',

            items: [{
                xtype: 'component',
                id: 'formErrorState2',
                baseCls: 'form-error-state',
                flex: 1,
                validText: 'V&aacute;lido',
                invalidText: 'Inv&aacute;lido',
                tipTpl: Ext.create('Ext.XTemplate', '<ul><tpl for="."><li><span class="field-name">{name}</span>: <span class="error">{error}</span></li></tpl></ul>'),

                getTip: function() {
                    var tip = this.tip;
                    if (!tip) {
                        tip = this.tip = Ext.widget('tooltip', {
                            target: this.el,
                            title: 'Error Details:',
                            autoHide: false,
                            anchor: 'top',
                            mouseOffset: [-11, -2],
                            closable: true,
                            constrainPosition: false,
                            cls: 'errors-tip'
                        });
                        tip.show();
                    }
                    return tip;
                },

                setErrors: function(errors) {
                    var me = this,
                        baseCls = me.baseCls,
                        tip = me.getTip();

                    errors = Ext.Array.from(errors);

                    // Update CSS class and tooltip content
                    if (errors.length) {
                        me.addCls(baseCls + '-invalid');
                        me.removeCls(baseCls + '-valid');
                        me.update(me.invalidText);
                        tip.setDisabled(false);
                        tip.update(me.tipTpl.apply(errors));
                    } else {
                        me.addCls(baseCls + '-valid');
                        me.removeCls(baseCls + '-invalid');
                        me.update(me.validText);
                        tip.setDisabled(true);
                        tip.hide();
                    }
                }
            }, {
                xtype: 'button',
                formBind: true,
                disabled: true,
                text: 'Recordar contrase&ntilde;a',
                width: 140,
                handler: function() {
                    var form = this.up('form').getForm();
                    var win = this.up('form').up('window');

                    if (form.isValid()) {
                    	form.submit({
                    		clientValidation: true,
                    		url: '../Accion/rememberUser',
                    		success: function(form, action) {
                    			Ext.MessageBox.alert('Informaci&oacute;n','Se acaba de enviar la contrase&ntilde;a al correo indicado de manera exitosa.');
                    		},
                    		failure: function(form, action) {
                    			Ext.MessageBox.alert('Informaci&oacute;n','Correo o contrase&ntilde;a incorrecta.');
                    		}
                    	});
                    }
                    
                }
            }, {
                xtype: 'button',
                formBind: true,
                disabled: true,
                text: 'Iniciar sesi&oacute;n',
                width: 100,
                handler: function() {
                    var form = this.up('form').getForm();
                    var win = this.up('form').up('window');

                    if (form.isValid()) {
                    	form.submit({
                    		clientValidation: true,
                    		url: '../Accion/loginUser',
                    		success: function(form, response) {
                   				usrType = response.result.usrType;
                    			win.close();
                    			
                    			if (usrType == '1'){
                    				 var ul = document.getElementById("menu");
                    				 var li = document.createElement("li");
                    				 var a = document.createElement("a");
                    				 
                    				 a.textContent = "Reportes";
                    				 a.setAttribute('href', "../Reportes.jsp");
                    				 li.appendChild(a);
                    				 ul.appendChild(li);
                    			}
                    		},
                    		failure: function(form, action) {
                    			Ext.MessageBox.alert('Informacion','Correo o contrase&ntilde;a incorrecta');
                    		}
                    	});
                    }
                    
                }
            }]
        }]
    });
    
    var window = Ext.widget('window',{
    	title: 'Iniciar Sesi&oacute;n',
    	id: 'window',
    	name: 'window',
    	modal:	true,
    	closable: false,
    	items: [formPanelLogin, formPanelRegister]
    });

    Ext.Ajax.request({
		url: '../Accion/isLoguedUser',
		method: 'POST',
		success: function(response, opts) {
			var result = JSON.parse(response.responseText);
			var mensaje = result.msg;
			var res = result.success;
			var usrType = result.usrType;
			
			if (!res){
				window.show();
			}else{
    			window.close();
    			
    			if (usrType == '1'){
    				 var ul = document.getElementById("menu");
    				 var li = document.createElement("li");
    				 var a = document.createElement("a");
    				 
    				 a.textContent = "Reportes";
    				 a.setAttribute('href', "../Reportes.jsp");
    				 li.appendChild(a);
    				 ul.appendChild(li);
    			}
    			
				dialog = Ext.MessageBox.show({
		           msg: 'Consultando asientos...',
		           width:300,
		           wait:true,
		           waitConfig: {interval:100}
		        });
			}
		},
		failure: function(response, opts) {
			var result = JSON.parse(response.responseText);
			var mensaje = result.msg;
			
			Ext.MessageBox.alert('Informaci&oacute;n', mensaje);
		},
	});
    
});