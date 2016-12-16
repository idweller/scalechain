package io.scalechain.blockchain.storage.index

import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.Matchers
import io.kotlintest.specs.FlatSpec
import java.io.File

import io.scalechain.blockchain.storage.Storage
import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class)
class TransactingRocksDatabasePerformanceSpec : FlatSpec(), Matchers, KeyValueDatabasePerformanceTrait {
  val testPath = File("./target/unittests-RocksDatabasePerformanceSpec")

  lateinit var transactingDB : TransactingRocksDatabase
  lateinit override var db : KeyValueDatabase

  override fun beforeEach() {
    testPath.deleteRecursively()
    testPath.mkdir()

    transactingDB = TransactingRocksDatabase( RocksDatabase( testPath ) )
    transactingDB.beginTransaction()
    db = transactingDB

    super.beforeEach()
  }

  override fun afterEach() {
    super.afterEach()

    transactingDB.commitTransaction()
    db.close()
  }

  init {
    Storage.initialize()
    addTests()
  }
}