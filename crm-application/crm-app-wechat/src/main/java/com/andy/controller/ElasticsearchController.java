package com.andy.controller;


import com.alibaba.fastjson.JSONObject;
import com.andy.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.List;

@Api(tags = "ElasticsearchController",description = "es api")
@RestController
@RefreshScope
public class ElasticsearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @ApiOperation(value = "添加索引")
    @RequestMapping(value = "/addIndex",method = RequestMethod.POST)
    public IndexResponse addIndex(@RequestBody User u, String indexName) throws Exception{
        String id = UUID.randomUUID().toString();
        IndexRequest request = new IndexRequest(indexName);
        request.id(u.getId().toString());
        String jsonString = JSONObject.toJSONString(u);
        request.source(jsonString, XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "使用get查询")
    @RequestMapping(value = "/getTest",method = RequestMethod.GET)
    public GetResponse getTest(String indexName,String id) throws Exception{
        GetRequest request = new GetRequest(indexName,id);
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "判断某个doc是否存在索引中")
    @RequestMapping(value = "/isExists",method = RequestMethod.GET)
    public boolean isExists(String indexName,String id) throws Exception{
        GetRequest request = new GetRequest(indexName,id);
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    @ApiOperation(value = "通过ID删除数据")
    @RequestMapping(value = "/delById",method = RequestMethod.DELETE)
    public DeleteResponse delById(String indexName,String id) throws Exception{
        DeleteRequest request = new DeleteRequest(indexName,id);
        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        return deleteResponse;
    }

    @ApiOperation(value = "通过ID修改数据")
    @RequestMapping(value = "/updateById",method = RequestMethod.PATCH)
    public UpdateResponse updateById(@RequestBody User u,String indexName,String id) throws Exception{
        UpdateRequest request = new UpdateRequest(indexName,id);
        request.doc(JSONObject.toJSONString(u),XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "通过IDs查询数据")
    @RequestMapping(value = "/multiGetRequest",method = RequestMethod.POST)
    public MultiGetResponse multiGetRequest(String indexName,@RequestBody List<String> ids) throws Exception{
        MultiGetRequest request = new MultiGetRequest();
        ids.forEach((id) -> {
            request.add(indexName,id);
        });
        MultiGetResponse response = restHighLevelClient.multiGet(request, RequestOptions.DEFAULT);
        return response;
    }
}
