Ext.define('jsapp.QueriesList', {
    extend: 'Ext.dataview.List',
    requires: ['Ext.data.Store', 'jsapp.QueryViewer'],
    
    constructor: function(config)
    {
    	config.itemTpl = '{name}';
    	config.store = Ext.create('jsapp.QueriesStore', {});
    	
    	config.listeners = {select: function(list, data, opts)
    			{
    				var queryViewer = Ext.create('jsapp.QueryViewer',{query: data.raw});
    				Ext.Viewport.add(queryViewer);
    				queryViewer.show();
    			}};
    	this.callParent(arguments);
    }
});