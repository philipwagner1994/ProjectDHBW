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

      // required due to lifecycle calls > init of undefined vars
      if (chartData === undefined) {
        return;
      }
      
      this._newCustomChart = c3.generate({
		    bindto: '#' + CHART_CANVAS_NAME_PREFIX + this.getId(),
		    data: {
		          json: chartData,
		          type: 'bar'
		        },
		        
		        bar: {
		            width: {
		                ratio: 0.5 // this makes bar width 50% of length between ticks
		            }
		            // or
		            //width: 100 // this makes bar width 100px
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
    }
  });
});