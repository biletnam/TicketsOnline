<%@page import="clases.SQLServerConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-Cache");
	response.setHeader("Pragma", "No-cache");
	
	String companyId = request.getParameter("companyId");
%>
<script src="https://www.paypalobjects.com/api/checkout.js"></script>
<div id="paypal-button-container" style="width: 50%; margin: 30 auto; text-align: center"></div>
<script>
	var amount = 0; 
	var artist = '<%=new SQLServerConnection(companyId).consultar1Valor("select titulo from tbEventos where eventoPkId = "+request.getSession().getAttribute("eventId").toString())%>';
	var place = '<%=new SQLServerConnection(companyId).consultar1Valor("select b.descripcion from tbEventos a join tbEspaciosLugares b on a.claveLugar = b.clave where a.eventoPkId = "+request.getSession().getAttribute("eventId").toString())%>';
	var dateTime = '<%=new SQLServerConnection(companyId).consultar1Valor("select CONVERT(varchar, FechaHora, 102) from tbEventos where eventoPkId = "+request.getSession().getAttribute("eventId").toString())%>';
	var seats = [];
	var key = window.parent.key;
    // Render the PayPal button

    paypal.Button.render({

        // Set your environment
        env: 'production', // sandbox | production

        // Specify the style of the button

        style: {
            layout: 'vertical',  // horizontal | vertical
            size:   'responsive',    // medium | large | responsive
            shape:  'pill',      // pill | rect
            color:  'gold'       // gold | blue | silver | black
        },

        // Specify allowed and disallowed funding sources
        //
        // Options:
        // - paypal.FUNDING.CARD
        // - paypal.FUNDING.CREDIT
        // - paypal.FUNDING.ELV

        funding: {
            allowed: [ paypal.FUNDING.CARD, paypal.FUNDING.CREDIT ],
            disallowed: [ ]
        },
        
        // PayPal Client IDs - replace with your own
        // Create a PayPal app: https://developer.paypal.com/developer/applications/create

        client: {
            sandbox:    'ASCFh3irOfSJ2Rlv1Eu71d50F9tdB858YsJDUcNiw9yCUqqjn7s4_Lo1k2WVWTyzyjIP2tduuPPbLYB3',
            //production: 'AWoN2LtRzcbea_5Cxp_xooNKDdAFyzxFsikiOicbQhbYtilQYMAZleA-_CJxBOts_iso5Qhdgi2k3KMx'		//CSA
            //production: 'AdAExoFCbiIPDSNtFMUKZqitUwoi-ICGY47_28spQjSL1G1dDDfozzNvbuNHAOw34UAMgQzgbLaLiTuT'	//JOE
            production: key	//JOE
        },

        payment: function(data, actions) {
        	window.top.document.getElementById('minute-counter').contentWindow.stop();
        	return actions.payment.create({
                "payment": {
                    "transactions": [
                        {
                            "amount": { 
                            	"total": amount, 
                            	"currency": 'MXN' 
                            },
                            "description": artist + '/' + place + '/' + dateTime + '/' +seats.toString()
                        }
                    ]
                }
            });
        },
        
        commit: true,

        onAuthorize: function(data, actions) {
            return actions.payment.execute().then(function() {
            	window.parent.window.showTicketReceipt('getReceipt?paymentID='+data.paymentID+'&sendMail=true&companyId=<%=companyId%>');
            });
        }
    }, '#paypal-button-container');

</script>
    