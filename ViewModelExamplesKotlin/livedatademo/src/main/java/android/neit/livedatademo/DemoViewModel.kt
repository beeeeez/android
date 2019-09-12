package android.neit.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DemoViewModel : ViewModel() {
    private val _result = MutableLiveData<String>()
    val result: MutableLiveData<String>
        get() = _result

    init {
        _result.value = "Hello, world!"
    }


}

//}
//    private var result: MutableLiveData<String>?  = MutableLiveData()
//
//   fun getResult(): MutableLiveData<String> {
//
//
//       if (result == null) {
//           return MutableLiveData()
//           // result = MutableLiveData()
//
//
//       } else {
//
//           // hockey but fixed the smart cast error
//           if (result != null) {
//               var test = result
//           }
//       }
//
//       var test1: MutableLiveData<String> = MutableLiveData()
//       return test1
//
//
//   }


