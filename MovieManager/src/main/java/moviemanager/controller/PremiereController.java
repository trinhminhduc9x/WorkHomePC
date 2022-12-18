package moviemanager.controller;

import moviemanager.dto.PremiereDto;
import moviemanager.model.Movie;
import moviemanager.model.Premiere;
import moviemanager.repository.PremiereRepository;
import moviemanager.service.IMovieService;
import moviemanager.service.IPremiereService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/premiere")
public class PremiereController {

    @Autowired
    IPremiereService iPremiereService;

    @Autowired
    IMovieService iMovieService;



    @ModelAttribute("movieList")
    public List<Movie> getListContract() {
        return iMovieService.ListMovie();
    }

    @GetMapping("/list")
    private String showerList(@PageableDefault(size = 9) Pageable pageable,
                              Model model,
                              @RequestParam(required = false, defaultValue = "") String numberPremiere,
                              @RequestParam(required = false, defaultValue = "") String dayPremiere,
                              @RequestParam(required = false, defaultValue = "") String movieId) {
        for (Sort.Order order : pageable.getSort()) {
            model.addAttribute("sortValue", order.getProperty());
        }
        model.addAttribute("premierePage", iPremiereService.fildPage(pageable,numberPremiere,dayPremiere,movieId));
        model.addAttribute("numberPremiere", numberPremiere);
        model.addAttribute("dayPremiere", dayPremiere);
        model.addAttribute("movieId", movieId);
        return "/premiere/list";
    }

    @PostMapping("/delete")
    private String delete(@RequestParam(name = "idDelete") Integer id, RedirectAttributes redirectAttributes) {
        iPremiereService.remove(id);
        redirectAttributes.addFlashAttribute("msg","xóa thành công");
        return "redirect:/premiere/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("premiereDto", new PremiereDto());
        return "/premiere/create";
    }

    @PostMapping("/save")
    public String save(@Validated
                       @ModelAttribute("premiereDto") PremiereDto premiereDto
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {
        new PremiereDto().validate(premiereDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "/premiere/create";
        } else {
            Premiere premiere = new Premiere();
            BeanUtils.copyProperties(premiereDto, premiere);
            iPremiereService.save(premiere);
            redirectAttributes.addFlashAttribute("msg", " Create form ok ");
            return "redirect:/premiere/create";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("premiereDto", iPremiereService.findById(id));
        return "/premiere/update";
    }


    @PostMapping("/update")
    public String update(@Validated
                         @ModelAttribute("premiereDto") PremiereDto premiereDto
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model) {
        new PremiereDto().validate(premiereDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "/premiere/update";
        } else {
            Premiere premiere = new Premiere();
            BeanUtils.copyProperties(premiereDto, premiere);
            iPremiereService.update(premiere);
            redirectAttributes.addFlashAttribute("msg", " update form ok ");
            return "redirect:/premiere/list";
        }
    }
}
