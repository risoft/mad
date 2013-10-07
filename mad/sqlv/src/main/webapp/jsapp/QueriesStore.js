Ext.define('jsapp.QueriesStore', {
    extend: 'Ext.data.Store',
    xtype: 'queriesstore',
    

    
    constructor: function(config)
    {
    	config.url = 'queries.json';
    	config.autoLoad = true;
    	config.proxy = {
    		type: 'ajax',
    		url: 'queries.json',
    		reader: {
    			type: 'json',
    			rootProperty: 'rows'
    		},
    	};
    	config.fields=["name"];
    	this.callParent(arguments);

    }

	
});

