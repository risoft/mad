Ext.define('jsapp.QueryViewer', {
    extend: 'Ext.Panel',
    xtype: 'queryviewer',
    constructor: function(config)
    {
    	console.log(config);
    	 var closeButton = {ui: 'back', text: 'Back',handler: function(button){  
    		 var viewer = button.up("queryviewer");
   		 	 viewer.destroy();
   	  	}};
    	 
    	 
    	 config.listeners = {
     			initialize: function(container, opts){
     				console.log("init");
     				Ext.Ajax.request({url: "query/"+config.query.id+"/metadata.json",
     					success: function(response, opts)
     					{
     						var json = Ext.JSON.decode(response.responseText);
     						if (false/*falsejson.rows.length == 1 && json.rows[0].length == 1*/)
     						{
     							var value = json.rows[0][0];
     							var title = json.columns[0];
     							var html = "<b>"+title+"</b><br/>"+value;
     							container.add({xtype: 'panel', html: html});
     						}
     						else
     						{
     							var reader = Ext.create('Ext.data.reader.Array', {rootProperty: 'rows'});
     							var proxy = Ext.create('Ext.data.proxy.Ajax', {url: "query/"+config.query.id+"/execute.json", reader:reader, idProperty: "internalRowNumber"});
     							var fields = ["internalRowNumber"].concat(json.columns);
     							var store =  Ext.create('Ext.data.ArrayStore', {
     								fields: fields, 
     								proxy: proxy, 
     								autoLoad: true, 
     								totalCount: json.total
 								});
     							
     							
     							store.addBeforeListener('load', function(store, records, isSuccessful){
     								console.log("%o", arguments);
								    var pageSize = store.getPageSize();
 								    var pageIndex = store.currentPage-1;    // Page numbers start at 1

 								    if(isSuccessful && records.length < pageSize)
 								    {
 								        //Set count to disable 'loading' message
 								        var totalRecords = pageIndex * pageSize + records.length;
 								        store.setTotalCount(totalRecords);
 								    }
 								    else
 								        store.setTotalCount(null);

     							});
     							
     							var plugins=  [   
     							                   {
     							                       xclass: 'Ext.plugin.ListPaging',
     							                       autoPaging: true
     							                   }
     							               ];
     							var fieldsList = {xtype: 'list', store: store, itemTpl: json.template, plugins: plugins};
     					   	 	container.add(fieldsList);
     						}
     					}
     					});
     			}
     	};
    	 
 
    
  	  	
   	  	this.callParent(arguments);
    }

    
});