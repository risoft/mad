Ext.application({
	requires: ['jsapp.QueriesList', 'jsapp.QueriesStore'],
      launch: function() {
    	  Ext.create('jsapp.QueriesList', {
    		    fullscreen: true
    		   
    		});

      }
  });
 
 
 