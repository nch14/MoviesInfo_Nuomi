package cn.chenhaonee.movie.nuomi.controller;

import cn.chenhaonee.movie.nuomi.movies.GetMovies;
import cn.chenhaonee.movie.nuomi.movies.MovieSnap;
import cn.chenhaonee.movie.nuomi.movies.Movies;
import cn.chenhaonee.movie.nuomi.vo.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenhaonee on 2017/6/9.
 */
@CrossOrigin
@Api(value = "电影信息", description = "")
@RequestMapping(value = "/movie")
@RestController
public class MovieController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseData<List<MovieSnap>> getAllRecentMovies() {
        List<MovieSnap> movieSnaps = new GetMovies().getRecentMovies();
        return new ResponseData<>(movieSnaps);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData<Movies> getMovieInfo(@PathVariable(value = "id") String id) {
        Movies movies = new GetMovies().GetMovieInfo(id);
        return new ResponseData<>(movies);
    }

}
