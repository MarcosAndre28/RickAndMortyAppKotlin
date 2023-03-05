import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.rickandmortyappkotlin.R
import com.example.rickandmortyappkotlin.data.repository.CharacterRepository
import com.example.rickandmortyappkotlin.data.viewModel.CharacterViewModel
import com.example.rickandmortyappkotlin.data.viewModel.CharacterViewModelFactory
import com.example.rickandmortyappkotlin.databinding.FragmentFilterBinding
import com.google.android.material.chip.Chip

class FilterDialogFragment : DialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val characterViewModel: CharacterViewModel by activityViewModels{ CharacterViewModelFactory(CharacterRepository()) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Crie um diálogo com o estilo de tema apropriado
        val dialog = Dialog(requireContext(), R.style.AlertDialogTheme)

        // Inflar o layout do diálogo
        _binding = FragmentFilterBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        // Configurar o tamanho do diálogo

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.BOTTOM)

        val window = dialog.window
        if (window != null){
            val params = window.attributes
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            params.gravity = Gravity.BOTTOM
            window.attributes = params
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        // Define os Chips
        binding.chipAlive.setOnClickListener {
            binding.chipAlive.isChecked = true
            binding.chipAlive.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.splash_color))
            binding.chipAlive.setChipBackgroundColorResource(R.color.white)
        }

        binding.chipDead.setOnClickListener {
            binding.chipDead.isChecked = true
            binding.chipDead.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.splash_color))
            binding.chipDead.setChipBackgroundColorResource(R.color.white)
        }

        binding.chipUnknown.setOnClickListener {
            binding.chipUnknown.isChecked = true
            binding.chipUnknown.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.splash_color))
            binding.chipUnknown.setChipBackgroundColorResource(R.color.white)
        }

        // Define o clique do botão Filtrar
        binding.btnMakeFilter.setOnClickListener {
            val selectedStatusChipId = binding.chipgroupStatus.checkedChipId
            val selectedGenderButtonId = binding.radiogroupGender.checkedRadioButtonId

            if(selectedStatusChipId != -1 && selectedGenderButtonId != -1) {
                val selectedStatusChip =  binding.root.findViewById<Chip>(selectedStatusChipId)
                val selectedGenderButton =  binding.root.findViewById<RadioButton>(selectedGenderButtonId)

                val selectedGender = selectedGenderButton?.text?.toString() ?: ""

                characterViewModel.getByStatusAndGender(selectedStatusChip.text.toString(), selectedGender, 1)
            } else if (selectedStatusChipId != -1) {
                val selectedStatusChip =  binding.root.findViewById<Chip>(selectedStatusChipId)
                characterViewModel.getByStatus(selectedStatusChip.text.toString(), 1)
            } else if (selectedGenderButtonId != -1) {
                val selectedGenderButton =  binding.root.findViewById<RadioButton>(selectedGenderButtonId)

                val selectedGender = selectedGenderButton?.text?.toString() ?: ""

                characterViewModel.getByGender(selectedGender, 1)
            } else {
                Toast.makeText(requireContext(), "Voce precisa selecionar algo no filtro", Toast.LENGTH_SHORT).show()
            }

            // Define o valor do filtro
            characterViewModel.filterValue.value = arrayOf(selectedStatusChipId, selectedGenderButtonId)

            dismiss()
        }

        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
