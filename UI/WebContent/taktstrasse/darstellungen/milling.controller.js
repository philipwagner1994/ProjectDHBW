sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/json/JSONModel',
		"sap/ui/core/routing/History"

	], function(jQuery, Controller, JSONModel, History ) {
	"use strict";
 
	var PageController = Controller.extend("sap.checkmarx.selfservice.taktstrasse.darstellungen.milling", {
		
		onInit : function (evt) {
			 var oModel = new sap.ui.model.json.JSONModel();
	           // Load JSON in model
	              //oModel.loadData("json/chartdata.json");
			this.getView().setModel(oModel);
			
			this.getRouter().attachRoutePatternMatched(this.onRouteMatched, this);
		},
		onRouteMatched: function() {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			this.getView().byId("Rows").setValue(oConfigModel.config.rows);
			this.getView().byId("CustomerNumSearch").setValue(oConfigModel.config.CustomerNum);
			this.getView().byId("MaterialNumSearch").setValue(oConfigModel.config.MaterialNum);
			oConfigModel.livedown = false;

			$.ajax({
			    async : false,
			    type : "GET",
			    url : "http://localhost:1234/Server/java",
			    dataType : 'text',
			    data : {
				'function' : "history_milling",
				history: oConfigModel.config.rows,
				materialno: oConfigModel.config.MaterialNum,
				customerno: oConfigModel.config.CustomerNum
			    },
			    success : function(response) {			
			    	var oModel = this.getView().getModel();
			    	var oController = this;
			    	var jsonResponse = JSON.parse(response);
			    	oModel.setProperty("/Data", jsonResponse);
			    	oModel.refresh(true);
			    	oController.getDataUpdate();
			    	
			    },
			    error : function(message) {
				console.error("Error");
			    }	
			});
		},
		getDataUpdate: function() {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			if(oConfigModel.livedown==false){
			$.ajax({
			    async : true,
			    type : "GET",
			    url : "http://localhost:1234/Server/java",
			    dataType : 'text',
			    data : {
				'function' : "live_milling",
				materialno: oConfigModel.config.MaterialNum,
				customerno: oConfigModel.config.CustomerNum
			    },
			    success : function(response) {
			    	var oModel = this.getView().getModel();
			    	var oController = this;
			    	
			    	var jsonResponse = JSON.parse(response);
			    	oModel.getProperty("/Data/speed").push(jsonResponse[0].speed);
			    	oModel.getProperty("/Data/speed").shift();
			    	oModel.getProperty("/Data/temp").push(jsonResponse[0].temp);
			    	oModel.getProperty("/Data/temp").shift();
			    	oModel.getProperty("/Data/orderno").push(jsonResponse[0].orderno);
			    	oModel.getProperty("/Data/orderno").shift();
			    	
			    	
			    	oModel.refresh(true);
			    	this.getView().byId("LineChartHeat").load();
			    	this.getView().byId("LineChartSpeed").load();
			    	this.getView().byId("GaugeChartHeat").load();
			    	this.getView().byId("GaugeChartSpeed").load();
			    	oController.getDataUpdate();
			    	
			    },
			    error : function(message) {
				console.error("Error");
			    }	
			});
			}
		},
		getRouter : function () {
			return sap.ui.core.UIComponent.getRouterFor(this);
		},
		onRowPress: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			var value = this.getView().byId("Rows").getValue();
			var oController = this;
			
			oConfigModel.livedown = true;
			if(value != undefined){
				oConfigModel.config.rows = value;
			}
			else oConfigModel.config.rows = "";
			oController.onRouteMatched();
		},
		onCustomerNumSearch: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			var value = this.getView().byId("CustomerNumSearch").getValue();
			var oController = this;
			
			oConfigModel.livedown = true;
			if(value != undefined){
				oConfigModel.config.CustomerNum = value;
			}
			else oConfigModel.config.CustomerNum = "";
			oController.onRouteMatched();
		},
		onMaterialNumSearch: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			var value = this.getView().byId("MaterialNumSearch").getValue();
			var oController = this;
			
			oConfigModel.livedown = true;
			if(value != undefined){
				oConfigModel.config.MaterialNum = value;
			}
			else oConfigModel.config.MaterialNum = "";
			oController.onRouteMatched();
		},
		onNavBack: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			oConfigModel.livedown = true;
			sap.ui.core.UIComponent.getRouterFor(this).navTo("overview");
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