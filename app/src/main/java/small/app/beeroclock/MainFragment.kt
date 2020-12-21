package small.app.beeroclock

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import small.app.beeroclock.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    lateinit var binding: FragmentMainBinding
    lateinit var model: Model
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)
        model = Model(requireContext())
        binding.model = model
        binding.lifecycleOwner = viewLifecycleOwner


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map.setTileSource(TileSourceFactory.MAPNIK)
        requestPermissionsIfNecessary(
            arrayOf(
                // if you need to show the current location, uncomment the line below
                // Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )

        button.setOnClickListener {
            model.resetCity()
        }

        model.lat.observe(viewLifecycleOwner, {
            setMapView(it, model.long.value!!)
        })

        model.long.observe(viewLifecycleOwner, {
            setMapView(model.lat.value!!, it)
        })
    }


    override fun onResume() {
        super.onResume()


        binding.model!!.resetCity()
        setMapView(model.lat.value!!, model.long.value!!)
    }

    private fun setMapView(
        lat: Double,
        long: Double
    ) {
        val mapController = binding.map.getController()
        mapController.setZoom(4.0)
        val startPoint = GeoPoint(lat, long)
        mapController.setCenter(startPoint)
        val startMarker = Marker(binding.map)
        startMarker.position = startPoint
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        if (binding.map.getOverlays().size == 1) binding.map.getOverlays().removeAt(0)
        binding.map.getOverlays().add(startMarker)

    }

    private fun requestPermissionsIfNecessary(permissions: Array<String>) {
        val permissionsToRequest: ArrayList<String> = ArrayList()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted
                permissionsToRequest.add(permission)
            }
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toArray(arrayOfNulls(0)),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }
}