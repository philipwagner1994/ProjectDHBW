sap.ui.define([
  'sap/ui/core/Control'
], function(Control) {
  'use strict';

  var CHART_CANVAS_NAME_PREFIX = 'LineChartHeat';

  return Control.extend('sap.checkmarx.selfservice.control.LineChartHeat', {
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

      // required due to lifecycle calls > init of undefined vars
      if (chartData === undefined) {
        return;
      }
      
      this._newCustomChart = c3.generate({
		    bindto: '#' + CHART_CANVAS_NAME_PREFIX + this.getId(),
		    data: {
		          json: chartData,
		          type: 'spline',
		          colors: {
		              data: '#0414A6'
		          },
		        },
		        
		        axis: {
		          y: {
		            label: {
		              text: 'Heat',
		              position: 'outer-middle'
		            },
		            tick: {
	              format: d3.format("°,") // ADD
		            }
		          },
		          x: {
			            label: {
			              text: 'Order Number',
			              position: 'outer-middle'
			            },
		          }
			           
		        }
      });
    },

    exit: function() {
      this._newCustomChart.destroy();
    },

    renderer: function(oRm, oControl) {
      //var oBundle = oControl.getModel('i18n').getResourceBundle();

      //Create the control
      oRm.write('<div');
      oRm.writeControlData(oControl);
      oRm.addClass("LineChartHeat");
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
    }
  });
});