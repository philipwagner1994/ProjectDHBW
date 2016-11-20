sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/json/JSONModel',
		"sap/ui/core/routing/History"

	], function(jQuery, Controller, JSONModel, History ) {
	"use strict";
 
	var PageController = Controller.extend("sap.checkmarx.selfservice.taktstrasse.darstellungen.production", {
		
		onInit : function (evt) {
			
			var oModel = new sap.ui.model.json.JSONModel();
			this.getView().setModel(oModel);
			
			var oView = this.getView();
			var that = this;
			var pic = oView.byId("pic");
			
			var img = new sap.ui.commons.Image({src: "img/Taktstrasse.JPG"});
			img.setWidth("1300px");
			pic.addContent(img);
			pic.addContent(
					new sap.ui.commons.Image({src: "img/Empty.JPG"}), {left: "510px", top: "400px"});

			var addIcons = function(oPosition, id){
				var icon = new sap.ui.commons.Button();
				icon.setIcon("sap-icon://lightbulb");
				var onPress = function(){
					openAlert(id);	
				};
				icon.attachPress(onPress)
				pic.addContent(icon, oPosition);
			};
			function openAlert(id) {
				var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
				if(oConfigModel.Data[id].OrderNum == undefined){
					sap.ui.commons.MessageBox.alert("No Data");
				}
				else{
					sap.ui.commons.MessageBox.alert("Order Number : "+ oConfigModel.Data[id].OrderNum + "\n"+
													"Customer Number: "+ oConfigModel.Data[id].CustomerNum + "\n"+
													"Material Number: "+ oConfigModel.Data[id].Value1 + "\n"+
													"Current Status: "+ oConfigModel.Data[id].Value2 + "\n"+
													"Timestamp: "+ oConfigModel.Data[id].timestamp);
					}
				}

			addIcons({left: "400px", top: "510px"}, 0);
			addIcons({left: "400px", top: "340px"}, 1);
			addIcons({left: "545px", top: "340px"}, 2);
			addIcons({left: "870px", top: "340px"}, 3);
			addIcons({left: "1170px", top: "378px"}, 4);
			
			this.getRouter().attachRoutePatternMatched(this.onRouteMatched, this);
			
		},
		onRouteMatched: function() {
			var that = this;
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();		
	    	var oView = that.getView();
	    	var pic = oView.byId("pic");
	    	var elements = pic.getContent();
			
			if(oConfigModel.productionlivedown==false){
			$.ajax({
			    async : true,
			    type : "GET",
			    url : "http://192.168.99.100:1234/Server/java",
			    dataType : 'text',
			     data : {
				command : "getWSData",
			    },
			    success : function(response) {
			    	if(response != "null"){
			    	
			    	
					var jsonResponse = JSON.parse(response);
					switch(jsonResponse[0].Item)
					{
						   case "L1":
						       if(jsonResponse[0].Value3 == "false"){
						    	   elements[2].setStyle("Accept");
						    	   oConfigModel.Data[0] = jsonResponse[0];
						       }
						       else elements[2].setStyle("Default");
						       break;
						   case "L2":
							   if(jsonResponse[0].Value3 == "false"){
						    	   elements[3].setStyle("Accept");
						    	   oConfigModel.Data[1] = jsonResponse[0];
						       }
						       else elements[3].setStyle("Default");
						       break; 
						   case "L3":
							   if(jsonResponse[0].Value3 == "false"){
						    	   elements[4].setStyle("Accept");
						    	   oConfigModel.Data[2] = jsonResponse[0];
						       }
						       else elements[4].setStyle("Default");
						       break;
						   case "MILLING_HEAT":
					    	   elements[4].setStyle("Accept");
					    	   oConfigModel.Data[2] = jsonResponse[0];
					       break;
						   case "MILLING_SPEED":
					    	   elements[4].setStyle("Accept");
					    	   oConfigModel.Data[2] = jsonResponse[0];
					       break;
						   case "L4":
							   if(jsonResponse[0].Value3 == "false"){
						    	   elements[5].setStyle("Accept");
						    	   oConfigModel.Data[3] = jsonResponse[0];
						       }
						       else elements[5].setStyle("Default");
						       break;
						   case "DRILLING_HEAT":
					    	   elements[5].setStyle("Accept");
					    	   oConfigModel.Data[3] = jsonResponse[0];
					       break;
						   case "DRILLING_SPEED":
					    	   elements[5].setStyle("Accept");
					    	   oConfigModel.Data[3] = jsonResponse[0];
					       break;
						   case "L5":
							   if(jsonResponse[0].Value3 == "false"){
						    	   elements[6].setStyle("Accept");
						    	   oConfigModel.Data[4] = jsonResponse[0];
						       }
						       else elements[6].setStyle("Default");
						       break;
						   default:
					}
   	
			    	}
			    	that.onRouteMatched();
			    },
			    error : function(message) {
			    }	
			});
			}
		},
		getRouter : function () {
			return sap.ui.core.UIComponent.getRouterFor(this);
		},
		onNavBack: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			oConfigModel.livedown = true;
			sap.ui.core.UIComponent.getRouterFor(this).navTo("overview");
		},

	});
 
	return PageController;
	


	       
/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf tacktstrasse.uebersicht
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf tacktstrasse.uebersicht
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf tacktstrasse.uebersicht
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf tacktstrasse.uebersicht
*/
//	onExit: function() {
//
//	}

});