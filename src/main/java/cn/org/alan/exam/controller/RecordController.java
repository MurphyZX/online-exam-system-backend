package cn.org.alan.exam.controller;

import cn.org.alan.exam.common.result.Result;
import cn.org.alan.exam.model.vo.record.ExamRecordDetailVO;
import cn.org.alan.exam.model.vo.record.ExamRecordVO;
import cn.org.alan.exam.model.vo.record.ExerciseRecordDetailVO;
import cn.org.alan.exam.model.vo.record.ExerciseRecordVO;
import cn.org.alan.exam.service.IExerciseRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 考试记录
 *
 * @Author Alan
 * @Version
 * @Date 2024/3/25 11:22 AM
 */
@RestController
@RequestMapping("/api/records")
public class RecordController {
    @Resource
    private IExerciseRecordService exerciseRecordService;

    /**
     * 分页查询已考试试卷
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param examName 考试名称
     * @param isASC 是否升序排列，true为升序，false为降序，默认为false
     * @return 查询结果
     */
    @GetMapping("/exam/paging")
    @PreAuthorize("hasAnyAuthority('role_teacher','role_admin','role_student')")
    public Result<IPage<ExamRecordVO>> getExamRecordPage(@RequestParam(value = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize",required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "examName", required = false) String examName,
                                                   @RequestParam(value = "isASC", required = false, defaultValue = "false") Boolean isASC){
        return exerciseRecordService.getExamRecordPage(pageNum, pageSize, examName, isASC);
    }

    /**
     * 查询试卷详情
     * @param examId
     * @return
     */
    @GetMapping("/exam/detail")
    @PreAuthorize("hasAnyAuthority('role_teacher','role_admin','role_student')")
    public Result<List<ExamRecordDetailVO>> getExamRecordDetail(@RequestParam("examId") Integer examId){
        return exerciseRecordService.getExamRecordDetail(examId);
    }

    /**
     * 分页查询已考试刷题
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/exercise/paging")
    @PreAuthorize("hasAnyAuthority('role_teacher','role_admin','role_student')")
    public Result<IPage<ExerciseRecordVO>> getExerciseRecordPage(@RequestParam(value = "pageNum",required = false, defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize",required = false, defaultValue = "10") Integer pageSize,
                                                                 @RequestParam(value = "repoName", required = false) String repoName){
        return exerciseRecordService.getExerciseRecordPage(pageNum,pageSize,repoName);
    }

    /**
     * 查询刷题详情
     * @param exerciseId
     * @return
     */
    @GetMapping("/exercise/detail")
    @PreAuthorize("hasAnyAuthority('role_teacher','role_admin','role_student')")
    public Result<List<ExerciseRecordDetailVO>> getExerciseRecordDetail(@RequestParam("repoId") Integer exerciseId){
        return exerciseRecordService.getExerciseRecordDetail(exerciseId);
    }
}
