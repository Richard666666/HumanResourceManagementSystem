/**  
 *
 * @Title:  DownloadService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����8:37:10   
 * @version V1.0 
 * 
 */
package com.ljl.human.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.Dao.IDownloadDao;
import com.ljl.human.Dao.impl.DepartmentDao;
import com.ljl.human.Dao.impl.DownloadDao;
import com.ljl.human.pojo.Download;
import com.ljl.human.service.IDownloadService;
import com.ljl.human.utils.ObjectUtils;

/**  
 *
 * @Title:  DownloadService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����8:37:10   
 * @version V1.0 
 * 
 */
public class DownloadService implements IDownloadService{
	private IDownloadDao downloadDao=null;
	//ͨ�����췽����ֵ
 

	public DownloadService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	 downloadDao=(IDownloadDao) ObjectUtils.getObject("downloadDao");
	}

	/**   
	 * <p>Title: insertDownload</p>   
	 * <p>Description: </p>   
	 * @param download
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDownloadService#insertDownload(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public void insertDownload(Download download) throws SQLException {
		if(download.getDoDes().equals("")||download.getDoTitle().equals("")){
			throw new RuntimeException("�������Ĳ�������");
		}
		downloadDao.insertDownload(download);
		
	}

	/**   
	 * <p>Title: deleteDownloadById</p>   
	 * <p>Description: </p>   
	 * @param download
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDownloadService#deleteDownloadById(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public void deleteDownloadById(Download download) throws SQLException {
		if(download.getDoId()==null){
			throw new RuntimeException("idΪ��");
		}
		downloadDao.deleteDownloadById(download);
	}

	/**   
	 * <p>Title: updateDownloadById</p>   
	 * <p>Description: </p>   
	 * @param download
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDownloadService#updateDownloadById(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public void updateDownloadById(Download download) throws SQLException {
		if(download.getDoId()==null){
			throw new RuntimeException("idΪ��");
		}
		downloadDao.updateDownloadById(download);
		
	}

	/**   
	 * <p>Title: findDownloadByTitleLike</p>   
	 * <p>Description: </p>   
	 * @param download
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDownloadService#findDownloadByTitleLike(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public List<Download> findDownloadByTitleLike(Download download) throws SQLException {
		return downloadDao.findDownloadByTitleLike(download);
	}

	/**   
	 * <p>Title: findDownloadAll</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDownloadService#findDownloadAll()   
	 */  
	@Override
	public List<Download> findDownloadAll() throws SQLException {
			return downloadDao.findDownloadAll();
	}

	/**   
	 * <p>Title: findDownloadById</p>   
	 * <p>Description: </p>   
	 * @param download1
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IDownloadService#findDownloadById(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public Download findDownloadById(Download download) throws SQLException {
		if(download.getDoId()==null){
			System.out.println("id����Ϊ��");
		}
		return downloadDao.findDownloadById(download);
	}

	/**   
	 * <p>Title: findDownloadByTitle</p>   
	 * <p>Description: </p>   
	 * @param download
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IDownloadService#findDownloadByTitle(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public List<Download> findDownloadByTitle(Download download) throws SQLException {
		return downloadDao.findDepartmentByTitle(download);
	}


}
