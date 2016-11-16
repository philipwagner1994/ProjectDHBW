sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/json/JSONModel',
		"sap/ui/core/UIComponent",
		"sap/ui/core/routing/Router"
		

	], function(jQuery, Controller, JSONModel, UIComponent, Router ) {
	"use strict";

	var PageController = Controller.extend("sap.checkmarx.selfservice.taktstrasse.overview", {
		
		onInit : function (evt) {
			var oConfigModel = new sap.ui.model.json.JSONModel();
			oConfigModel.loadData("json/userconfig.json");
			sap.ui.getCore().setModel(oConfigModel, "ConfigModel");
			
			
			var oModel = new sap.ui.model.json.JSONModel();
	        oModel.loadData("json/data.json");
			this.getView().setModel(oModel);
			
			this.getRouter().attachRoutePatternMatched(this.onRouteMatched, this);
		},
		onRouteMatched: function() {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();	
			oConfigModel.livedown = false;
			var that = this;

			$.ajax({
			    async : true,
			    type : "GET",
			    url : "http://localhost:1234/Server/java",
			    dataType : 'text',
			    data : {
				command : "getWSData",
			    },
			    success : function(response) {
			    	var oModel = that.getView().getModel();
					
			    	var jsonResponse = JSON.parse(response);
			    	var OrderNum;
			    	console.log(jsonResponse);
			    	switch(jsonResponse[0].Item)
					{
						   case "MILLING_HEAT":
						       oModel.oData.TileCollection[0].number = jsonResponse[0].Value3;
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[0].OrderNum = OrderNum;
						       break;
						   case "MILLING_SPEED":
						       oModel.oData.TileCollection[1].number2 = jsonResponse[0].Value3;
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[1].OrderNum = OrderNum
						       break;
						   case "DRILLING_HEAT":
						       oModel.oData.TileCollection[0].number = jsonResponse[0].Value3
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[0].OrderNum = OrderNum
						       break;
						   case "DRILLING_SPEED":
						       oModel.oData.TileCollection[1].number2 = jsonResponse[0].Value3 
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[1].OrderNum = OrderNum
						       break;
						   default:
						       console.log("Fehler");
					}
			    	oModel.refresh(true);

			    	that.ajax();

			    },
			    error : function(message) {
				console.error("Error when trying to receive nodenameinformation.\nError: " + message);
			    }	
			});
		},
		getRouter : function () {
			return sap.ui.core.UIComponent.getRouterFor(this);
		},
 
		handleEditPress : function (evt) {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();	
			oConfigModel.livedown = true;
			
			var oModel = this.getView().getModel();
			var selectedId = evt.getParameter("id");
			var sub= selectedId.substring(selectedId.length-1, selectedId.length);
			var page = parseInt(sub);			
			
			switch(page) {
		    case 0:
		    	sap.ui.core.UIComponent.getRouterFor(this).navTo("milling");
		        break;
		    case 1:
		    	oModel.oData.flags[0].livedown = true;
				oModel.refresh(true);
		    	sap.ui.core.UIComponent.getRouterFor(this).navTo("drilling");
		        break;
		    case 2:
		    	oModel.oData.flags[0].livedown = true;
				oModel.refresh(true);
		    	sap.ui.core.UIComponent.getRouterFor(this).navTo("production");
		        break;
		    case 3:
		    	oModel.oData.flags[0].livedown = true;
				oModel.refresh(true);
		    	sap.ui.core.UIComponent.getRouterFor(this).navTo("errors");
		        break;
		    case 4:
		    	oModel.oData.flags[0].livedown = true;
				oModel.refresh(true);
		    	sap.ui.core.UIComponent.getRouterFor(this).navTo("details");
		        break;
		    case 5:
		    	oModel.oData.flags[0].livedown = true;
				oModel.refresh(true);
		    	sap.ui.core.UIComponent.getRouterFor(this).navTo("runtime");
		        break;
		    default:
		        alert("Systemerror please call your Systemadminestator");
		}
			
		},
		ajax : function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();	
			if(oConfigModel.livedown == false){

			$.ajax({
			    async : true,
			    type : "GET",
			    url : "http://localhost:1234/Server/java",
			    dataType : 'text',
			    data : {
				command : "getWSData",
			    },
			    success : function(response) {
			    	var oModel = this.getView().getModel();
					var oController = this;
			    	var jsonResponse = JSON.parse(response);
			    	var OrderNum;
			    	console.log(jsonResponse);
			    	switch(jsonResponse[0].Item)
					{
						   case "MILLING_HEAT":
						       oModel.oData.TileCollection[0].number = jsonResponse[0].Value3;
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[0].OrderNum = OrderNum;
						       break;
						   case "MILLING_SPEED":
						       oModel.oData.TileCollection[0].number2 = jsonResponse[0].Value3;
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[0].OrderNum = OrderNum
						       break;
						   case "DRILLING_HEAT":
						       oModel.oData.TileCollection[1].number = jsonResponse[0].Value3
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[1].OrderNum = OrderNum
						       break;
						   case "DRILLING_SPEED":
						       oModel.oData.TileCollection[1].number2 = jsonResponse[0].Value3 
						       OrderNum =  "OrderNum: " + jsonResponse[0].OrderNum;
						       oModel.oData.TileCollection[1].OrderNum = OrderNum
						       break;
						   default:
						       console.log("Fehler");
					}
			    	oModel.refresh(true);
			    	console.log(oModel.oData);

			    	oController.ajax();

			    },
			    error : function(message) {
				console.error("Error when trying to receive nodenameinformation.\nError: " + message);
			    }	
			});
			}
		}		
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