package com.example.googlemapsdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope

import com.example.googlemapsdemo.databinding.ActivityMapsBinding
import com.example.googlemapsdemo.misc.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typeAndStyle = TypeAndStyle()
//    private val cameraAndViewport = CameraAndViewport()

    // Add a marker in Sydney and move the camera
    private val tokyo = LatLng(35.68879981093124, 139.77244454788328)
    private val taiwan = LatLng(23.459621515018753, 121.20255613443851)
    private val hokkaido = LatLng(43.34528002870465, 142.6073483968095)
    private val beijing = LatLng(39.92169741521567, 116.40292456525955)


    // lazyを使ったインスタンス作成
    private val cameraAndViewport by lazy {CameraAndViewport()}
    private val shapes by lazy { Shapes() }
    private val overlays by lazy { Overlays() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    // 地図の種類を選択することができる
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, mMap)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // draggableをtrueにすることでマーカーを移動させることができる
        val hokkaidoMarker = mMap.addMarker(MarkerOptions()
            .position(hokkaido).title("Marker in Tokyo")
            .title("Marker in Tokyo")
            .snippet("Some random text"))

        val tokyoMarker2 = mMap.addMarker(MarkerOptions()
            .position(taiwan).title("Marker in Tokyo2")
            .title("Marker in Tokyo2")

             // スニペットとは断片という意味
             // マーカーが2つ以上存在して、片方のwindowが表示された場合に
             // もう片方が非表示になる
            .snippet("Some random text")

            // 2つのマーカーが重なった場合の重なり
            .zIndex(1f))

             // 地図の向きを変更してもマーカーはの向きは変わらない
//            .flat(true))

            // 濃淡
//            .alpha(0.5f)

            // 傾き
//            .rotation(90f))
//            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))
//            .icon(BitmapDescriptorFactory.defaultMarker(26f)))

            // このままだとクラッシュしてしまう
//            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_android_black_24dp)))
//            .icon(fromVectorToBitmap(R.drawable.ic_android_black_24dp, Color.parseColor(("#000000")))))


        hokkaidoMarker.tag = "Restaurant"

//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.tokyo))

        // newLatLngZoomにすることで初期表示する際のズームレベルを指定できる
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hokkaido, 7f))
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.tokyo))
        mMap.uiSettings.apply {

            // ズームボタンが表示される
            isZoomControlsEnabled = true

            // falseにするとズームジェスチャーが使えなくなる
//            isZoomGesturesEnabled = false

            // スクロールできなくする
//            isScrollGesturesEnabled = false

            // location layerをenabledにしないと反映されない
//            isMyLocationButtonEnabled = true

            // 地図を回転させる場合のみ表示される
//            isCompassEnabled = true
        }

        // 地図上に余白を加えることができる、中心がずれるため、ズームした際にずれる
//        mMap.setPadding(0, 0, 300, 0)

//        mMap.setOnPolylineClickListener(this)

//        mMap.setOnMarkerClickListener(this)

        typeAndStyle.setMapStyle(mMap, this)

        // ロケーションボタンを表示する
        checkLocationPermission()
//        mMap.setOnMarkerClickListener(this)
//        mMap.setMinZoomPreference(15f)
//        mMap.setMaxZoomPreference(17f)

        // 4000ミリ秒後にズームする
//        lifecycleScope.launch {
//            delay(4000L)
//            mMap.moveCamera(CameraUpdateFactory.zoomBy(3f))
//        }

//        onMapClicked()
//        onMapLongClicked()

//        mMap.setOnMarkerDragListener(this)

        // 4つの点を結んで形を作る（四角形）
//        shapes.addPolygon(mMap)



//        val groundOverlay = overlays.addGroundOverlay(mMap)

        // 4000ミリ秒後にニューヨークに移動する（ピンは東京のまま）
        lifecycleScope.launch {
//            delay(4000L)
////            groundOverlay.remove()
//            groundOverlay.transparency = 0.5f

//            shapes.addPolyline(mMap)
//            shapes.addCircle(mMap)
//            delay(2000L)
//            addPolyline()

            // マーカーを消す
//            tokyoMarker.remove()
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.tokyo), 1000, object :
//            GoogleMap.CancelableCallback{
//                override fun onFinish() {
//                    Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
//                }
//
//                // アニメーションの途中で操作した場合に呼び出される
//                override fun onCancel() {
//                    Toast.makeText(this@MapsActivity, "Cancelled", Toast.LENGTH_SHORT).show()
//                }
//            })

            // ズーム（アニメーション）
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f), 1000, null)


            //　カメラが右に移動する（アニメーション）
//            mMap.animateCamera(CameraUpdateFactory.scrollBy(200f, 0f), 2000 , null)



//            mMap.moveCamera(CameraUpdateFactory.newLatLng(newyork))

            // ピンはそのままで、地図が移動する
//            mMap.moveCamera(CameraUpdateFactory.scrollBy(100f, 100f))

            // 南西、北東の緯度経度を指定
            // 画面に指定バウンディングボックス＋パディングの領域がおさまるようにカメラが移動する
//            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100))

            // 最初に表示された都市から途切れずに移動する
//            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100), 2000, null)

            // ズーム
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center, 10f))

            // スクロールできるエリアを制限する
//            mMap.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)

        }

        // カスタマイズしたinfoを表示させることができる
//        mMap.setInfoWindowAdapter(CustomInfoAdapter(this))
    }


//    override fun onPoiClick(p0: PointOfInterest) {
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
//    }

//    override fun onPolylineClick(p0: Polyline) {
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
//    }

//    override fun onMarkerClick(marker: Marker?): Boolean {
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null)
//        marker?.showInfoWindow()
//        return true
//    }

//    // ドラッグ&ドロップした場合にログを表示する
//    override fun onMarkerDragStart(p0: Marker) {
//        Log.d("Drag", "Start")
//    }
//
//    override fun onMarkerDrag(p0: Marker) {
//        Log.d("Drag", "Drag")
//    }
//
//    override fun onMarkerDragEnd(p0: Marker) {
//        Log.d("Drag", "End")
//    }

    // マーカーをクリックするとタグが表示される
//    override fun onMarkerClick(marker: Marker?): Boolean {
//        if(marker != null) {
//            Log.d("Marker", marker.tag as String)
//        } else {
//            Log.d("Marker", "Empty")
//        }
//
//        // trueにするとRunに表示される
//        // falseにすることでアプリにタグ名が表示される
//        return false
//    }

//    private fun onMapClicked(){
//        mMap.setOnMapClickListener {
//          Toast.makeText(this@MapsActivity, "Single Click", Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun onMapLongClicked() {
//        mMap.setOnMapLongClickListener {
////            Toast.makeText(this@MapsActivity, "Long Click", Toast.LENGTH_SHORT).show()
//
//            // クリックした場所の緯度と経度が表示される
//            Toast.makeText(this@MapsActivity, "${it.latitude}, ${it.longitude}", Toast.LENGTH_SHORT).show()
//            mMap.addMarker((MarkerOptions().position(it).title("Marker in Tokyo")))
//        }
//    }

    private fun checkLocationPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
            Toast.makeText(this, "Already Enabled", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode != 1){
           return
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Granted!", Toast.LENGTH_SHORT).show()
            mMap.isMyLocationEnabled = true
        } else {
            Toast.makeText(this, "We need your permission!", Toast.LENGTH_SHORT).show()
        }
    }
}