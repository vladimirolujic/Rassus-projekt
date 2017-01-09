package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
@RequestMapping("/walks/*")
public class WebWalksController {

	@Autowired
	protected WebWalksService walksService;
	
	@Autowired
	protected UserService userService;

	public WebWalksController(WebWalksService walksService) {
		this.walksService = walksService;
	}

	@RequestMapping("/dog/{dogId}")
	public String byDog(Model model, @PathVariable("dogId") Long dogId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByDogId(dogId);
		model.addAttribute("dogWalks", walks);
		return "dog-walks";
	}

	@RequestMapping("/walker/{walkerId}")
	public String byWalker(Model model, @PathVariable("walkerId") Long walkerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(walkerId);
		model.addAttribute("walkerWalks", walks);
		return "walker-walks";
	}

	@RequestMapping("/owner/{ownerId}")
	public String byOwner(Model model, @PathVariable("ownerId") Long ownerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(ownerId);
		model.addAttribute("ownerWalks", walks);
		return "owner-walks";
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activeWalks(Model model) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerIdIsNull();
		System.out.println("setnje");
		System.out.println(walks.get(0).getPrice());
		model.addAttribute("activeWalks", walks);
		return "active-walks";
	}
/*
	@RequestMapping(value = "/offer", method = RequestMethod.POST)
	public Walk offerWalk(@RequestBody Walk walk) {
		return walksService.offerWalk(walk);
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public Walk acceptOffer(@RequestBody Walk walk) {
		return walksService.acceptOffer(walk);
	}
*/
	@RequestMapping(value = "/users")
	public String byUser(Model model) {
//		List<User> users = new ArrayList<User>();
//		users = (List<User>) userService.findById(userId);
		model.addAttribute("user", new User());
		return "user-walks";
	}
	
	@GetMapping("/users")
    public String usersForm(Model model) {
        model.addAttribute("user", new User());
        return "user-walks";
    }

    @PostMapping("/users")
    public String usersSubmit(@ModelAttribute User user) {
        return "user-result";
    }
	
//	@PostMapping("/users")
//    public String greetingSubmit(@ModelAttribute User user) {
//		
//        return "user-walks";
//         
//    }
}
