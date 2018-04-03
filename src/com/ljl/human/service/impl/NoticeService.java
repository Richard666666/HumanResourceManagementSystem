/**  
 *
 * @Title:  NoticeService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:39:58   
 * @version V1.0 
 * 
 */
package com.ljl.human.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.Dao.INoticeDao;
import com.ljl.human.pojo.Notice;
import com.ljl.human.service.INoticeService;
import com.ljl.human.utils.ObjectUtils;

import jdk.nashorn.internal.ir.ThrowNode;

/**  
 *
 * @Title:  NoticeService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:39:58   
 * @version V1.0 
 * 
 */
public class NoticeService implements INoticeService{
	private INoticeDao noticeDao=null;
	public NoticeService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		noticeDao=(INoticeDao) ObjectUtils.getObject("noticeDao");
	}

	/**   
	 * <p>Title: insertNotice</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @throws SQLException   
	 * @see com.ljl.human.service.INoticeService#insertNotice(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public void insertNotice(Notice notice) throws SQLException {
		if(notice.getnContent().equals("")||notice.getnName().equals("")){
			throw new RuntimeException("输入的参数不够");
		}
		noticeDao.insertNotice(notice);
	}

	/**   
	 * <p>Title: updateNoticeById</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @throws SQLException   
	 * @see com.ljl.human.service.INoticeService#updateNoticeById(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public void updateNoticeById(Notice notice) throws SQLException {
		if(notice.getnId()==null){
			throw new RuntimeException("输入的id不能为空");
		}
		noticeDao.updateNoticeById(notice);
		
	}

	/**   
	 * <p>Title: findNoticeAll</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.INoticeService#findNoticeAll()   
	 */  
	@Override
	public List<Notice> findNoticeAll() throws SQLException {
	
		return 	noticeDao.findNoticeAll();
	}

	/**   
	 * <p>Title: findNoticeByNameAndContentLike</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.INoticeService#findNoticeByNameAndContentLike(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public List<Notice> findNoticeByNameAndContentLike(Notice notice) throws SQLException {
		
		return noticeDao.findNoticeByNameAndContentLike(notice);
	}

	/**   
	 * <p>Title: findNoticeById</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.INoticeService#findNoticeById(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public Notice findNoticeById(Notice notice) throws SQLException {
		
		return noticeDao.findNoticeById(notice);
	}

	/**   
	 * <p>Title: deleteNoticeById</p>   
	 * <p>Description: </p>   
	 * @param notice   
	 * @throws SQLException 
	 * @see com.ljl.human.service.INoticeService#deleteNoticeById(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public void deleteNoticeById(Notice notice) throws SQLException {
		if(notice.getnId()==null){
			throw new RuntimeException("Id为null不能删除数据");
		}
		noticeDao.deleteNoticeById(notice);
		
	}

	/**   
	 * <p>Title: findNoticeByName</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.INoticeService#findNoticeByName(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public List<Notice> findNoticeByName(Notice notice) throws SQLException {
		return noticeDao.findNoticeByName(notice);
	}

}
