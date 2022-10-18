package youngjun.me.issueservice.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import youngjun.me.issueservice.domain.Issue
import youngjun.me.issueservice.domain.IssueRepository
import youngjun.me.issueservice.domain.enums.IssueStatus
import youngjun.me.issueservice.model.IssueRequest
import youngjun.me.issueservice.model.IssueResponse

@Service
class IssueService(
    private val issueRepository: IssueRepository
) {
    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {

        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId, // Token 기반으로 복호화
            type = request.type,
            priority = request.priority,
            status = request.status
        )
        return IssueResponse(issueRepository.save(issue))
    }

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map { IssueResponse(it) }
}