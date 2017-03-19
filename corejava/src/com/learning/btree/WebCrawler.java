package com.learning.btree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class WebCrawler{
	
	private final static String BASE_SITE = "http://www.danstools.com/";
	
	private static AtomicInteger numVisited = new AtomicInteger();
	
	PageNode root;
	
	static class PageNode {
		String baseUrl;
		Map<String, PageNode> children = new HashMap<>();
		boolean visited;
		String data;
		
		public void print(StringBuilder sb, PageNode p) {
			if(p == null) {
				return;
			}
			System.out.println(sb+p.baseUrl);
			Iterator<String> childI = p.children.keySet().iterator();
			while(childI.hasNext()) {
				PageNode nn = p.children.get(childI.next());
				print(sb, nn);
			}
			//sb.append("\t");
		}
	}
	
	static class CrawlTask implements Callable<PageNode> {
		PageNode node;
		public CrawlTask(PageNode node) {
			this.node = node;
		}
		@Override
		public PageNode call() throws Exception {
			try {
				if(node.visited) {
					System.out.println("cycle "+node.baseUrl);
					return node;
				}
/*				if(numVisited.get() >= 5) {
					System.out.println("visited 5 urls .. done");
					return node;
				}*/
				URL urlObj = new URL(node.baseUrl);
				URLConnection conn = urlObj.openConnection();
				conn.setConnectTimeout(1000);
				InputStreamReader streamReader = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(streamReader);
				String data;
				
				node.visited = true;
				numVisited.getAndIncrement();
				StringBuilder text = new StringBuilder();
				while((data = br.readLine() ) != null) {
					PageNode child = parseLine(data);
					if(child != null) {
						if(child.baseUrl.equals(node.baseUrl)) {
							child.visited = true;
						}
						node.children.put(child.baseUrl, child);
					}
					//text.append(data);
					text.append("\n");
					
				}
				br.close();
				node.data = text.toString();
				return node;
			} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
			}	
			return node;
		}
		
		/*
		 * get 1 url per line
		 */
		PageNode parseLine(String str) {
			PageNode pn = null;

			String url = "";
			if(str != null && str.indexOf("a href=\"") != -1) {
				int start = str.indexOf("a href=\"");
				int end = str.indexOf("\"", start+"a href=\"".length());
				if(end > start) {
					pn = new PageNode();
					url = str.substring(start+"a href=\"".length(), end);
					if(!url.startsWith("http:")) {
						if(url.startsWith("//")) {
							pn.baseUrl = "http:"+url;
						}
						pn.baseUrl = BASE_SITE+url;
					} else {
						pn.baseUrl = url;
					}
				}
			}
			return pn;
		}
	}


	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		WebCrawler crawler = new WebCrawler();
		crawler.root = new PageNode();
		crawler.root.baseUrl = BASE_SITE;
		CrawlTask task = new CrawlTask(crawler.root);
		Future<PageNode> fnode = service.submit(task);
		PageNode value = fnode.get();
		Iterator<String> childI = value.children.keySet().iterator();
		
		List<Future<PageNode>> futures = new ArrayList<>();
		while(childI.hasNext()) {
			PageNode nn = value.children.get(childI.next());
			CrawlTask childTask = new CrawlTask(nn);
			Future<PageNode> tnode = service.submit(childTask);
			futures.add(tnode);
		}
		for(Future<PageNode> f : futures) {
			PageNode pn = f.get();
			System.out.println(pn.baseUrl);
		}
		service.shutdown();
		crawler.root.print(new StringBuilder(), crawler.root);
		
	}

}
