sap.ui.define([
  'sap/ui/core/Control'
], function(Control) {
  'use strict';

  var CHART_CANVAS_NAME_PREFIX = 'BarChartErrors';

  return Control.extend('sap.checkmarx.selfservice.control.BarChartErrors', {
    metadata: {
      properties: {
        data: {
          type: 'object'
        }
      },
      events: {
        unload: {
          enablePreventDefault: true,
          chartIDs: {
            type: 'string[]'
          }
        }
      }
    },

    init: function() {
      var _newCustomChart;
    },

    onBeforeRendering: function() {

    },

    onAfterRendering: function() {
    	var chartData = this.getData();
        var data =  [];
        if(chartData != undefined ){
     	   for(var i=0;i<chartData.length;i++){
     		   data[i+1] = chartData[i].Errors;
     	   }
        }

      // required due to lifecycle calls > init of undefined vars
      if (chartData === undefined) {
        return;
      }
      
      this._newCustomChart = c3.generate({
		    bindto: '#' + CHART_CANVAS_NAME_PREFIX + this.getId(),
		    data: {
 		    	empty: {
 		    	    label: {
 		    	      text: "No Data"
 		    	    }
 		    	  },
 		    	columns:[data],
 		          type: 'bar',
     		      colors: {
   		              data: '#0414A6'
   		          },
 		        },
		        
		        bar: {
		            width: {
		                ratio: 0.5 
		            }
		        },
		        axis: {
			          y: {
			            label: {
			              text: 'Number of Errors',
			              position: 'outer-middle'
			            }
			          },
			          x: {
		 		          show: false,
				        	  
				              height: 130
				          }
				           
			        },
		        legend: {
		        	  show: false
		        	},
		        	tooltip: {
		        		  contents: function (d, defaultTitleFormat, defaultValueFormat, color) {
		        			  var table = "<html><body><head><style>table { border: 1px solid;border-color: #D8D8D8;background-color: #D8D8D8;border-space: 0px;}#header { background-color: #D8D8D8;color: white;}#input { background-color: white;}#value { text-align: right;}		</style> </head><table><tr id=header><th colspan=2>"+ chartData[d[0].index].timestamp +"</th></tr><tr id=input><td>Heat</td></tr><tr id=input><td>Errors</td><td id=value>"+ chartData[d[0].index].Errors +"</td></tr></table></body></html>";
		        		    return table;
		        		  }
		        		}
		        
      });
    },

    exit: function() {
      this._newCustomChart.destroy();
    },

    renderer: function(oRm, oControl) {
      //Create the control
      oRm.write('<div');
      oRm.writeControlData(oControl);
      oRm.addClass("BarChartErrors");
      oRm.addClass("sapUiResponsiveMargin");
      oRm.writeClasses();
      oRm.write('>');

      oRm.write('<div id="' + CHART_CANVAS_NAME_PREFIX + oControl.getId() + '"></div>');

      oRm.write('</div>');
    },

    unload: function(chartIDs) {
      this._newCustomChart.unload({
        ids: chartIDs
      });
    },
    load: function(){
    	var chartData = this.getData();
       	var data =  [];
        if(chartData != undefined ){
     	   for(var i=0;i<chartData.length;i++){
     		   data[i+1] = chartData[i].Errors;
     	   }
        }
       	 this._newCustomChart.load({
       		columns:[data]
       	 })
   }
  });
});