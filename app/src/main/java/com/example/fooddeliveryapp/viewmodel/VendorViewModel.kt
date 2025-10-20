package com.example.fooddeliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.entity.Vendor
import com.example.fooddeliveryapp.data.repository.VendorRepository
import com.example.fooddeliveryapp.viewmodel.state.VendorUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VendorViewModel(private val repository: VendorRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(VendorUiState())
    val uiState: StateFlow<VendorUiState> = _uiState.asStateFlow()

    private val _currentVendor = MutableLiveData<Vendor>()
    val currentVendor: LiveData<Vendor> = _currentVendor

    /**
     * Load a specific vendor by ID.
     */
    fun loadVendor(id: Long) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.getVendorById(id).collect { vendor ->
                _uiState.value = VendorUiState(
                    vendor = vendor,
                    isLoading = false
                )
            }
        }
    }
    class VendorViewModel(private val repository: VendorRepository) : ViewModel() {
        fun updateVendor(vendor: Vendor) {
            viewModelScope.launch {
                repository.updateVendor(vendor)
            }
        }
    }


    /**
     * Update vendor information (name, phone, email, etc.)
     */
    fun updateVendor(vendor: Vendor) {
        viewModelScope.launch {
            repository.updateVendor(vendor)
            _uiState.value = _uiState.value.copy(vendor = vendor)
        }
    }

    /**
     * Toggle vendor online/offline status.
     */
    fun toggleStatus(vendor: Vendor) {
        viewModelScope.launch {
            val updatedVendor = vendor.copy(isOnline = !vendor.isOnline)
            repository.updateVendor(updatedVendor)
            _uiState.value = _uiState.value.copy(vendor = updatedVendor)
        }
    }
}
