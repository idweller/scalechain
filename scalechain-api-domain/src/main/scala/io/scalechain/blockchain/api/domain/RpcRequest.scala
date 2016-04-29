package io.scalechain.blockchain.api.domain

import spray.json._

// BUGBUG : A Int value may come as an element of params.
// Ex> ["abc", 1]
case class RpcRequest(jsonrpc:String, id:String, method:String, params:RpcParams)